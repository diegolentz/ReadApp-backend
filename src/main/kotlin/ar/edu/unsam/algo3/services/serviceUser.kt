package ar.edu.unsam.algo3.services

import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.*
import ar.edu.unsam.algo3.DTO.*
import ar.edu.unsam.algo3.mock.USERS
import excepciones.BusinessException
import excepciones.NotFoundException
import org.springframework.stereotype.Service


@Service
object ServiceUser {
    private val userRepository: Repositorio<Usuario> = Repositorio()

    init {
        USERS.forEach { user ->
            userRepository.create(user)
        }

        val libros = ServiceLibros.get()

        val usuarios = this.getAll()

        val diego = usuarios[0]
        diego.agregarLibroALeer(libros[0])
        diego.agregarLibroALeer(libros[1])
        diego.agregarLibroALeer(libros[2])
        diego.agregarLibroALeer(libros[3])
        diego.agregarLibroALeer(libros[4])
        diego.agregarLibroALeer(libros[5])

        diego.leer(libros[6])
        diego.leer(libros[7])
        diego.leer(libros[8])
        diego.leer(libros[9])

        diego.agregarAmigo(usuarios[1])
        diego.agregarAmigo(usuarios[2])
        diego.agregarAmigo(usuarios[3])

        

    }

    fun getAll(): List<Usuario> = userRepository.getAll().toList()

    fun getByIdRaw(idTypeString: String): Usuario {
        val usuario: Usuario?
        try {
            val idTypeNumber = Integer.valueOf(idTypeString)
            usuario = userRepository.getByID(idTypeNumber)
        } catch (err: NumberFormatException) {
            throw BusinessException("$idTypeString no es un entero, ingrese un id válido")
        }
        return usuario
    }

    fun getByIdBasic(idTypeString: String): UserBasicDTO {
        return this.getByIdRaw(idTypeString).toDTOBasic()
    }

    fun getByIdProfile(idTypeString: String): UserProfileDTO {
        return this.getByIdRaw(idTypeString).toDTOProfile()
    }

    fun getByIdFriends(idTypeString: String): List<UserFriendDTO> {
        val usuarioLogueado = this.getByIdRaw(idTypeString)
        return usuarioLogueado.amigos.map { it.toDTOFriend() }
    }

    fun getByIdNotFriends(idTypeString: String): List<UserFriendDTO>  {
        val usuarios = this.getAll().toMutableList()
        val usuarioLogueado = this.getByIdRaw(idTypeString)
        usuarios.remove(usuarioLogueado)
        return this.notMyFriends(usuarios, usuarioLogueado)
    }

    private fun notMyFriends(usuarios: List<Usuario>, usuarioLogueado: Usuario): List<UserFriendDTO> {
        return usuarios.filter { !usuarioLogueado.esAmigoDe(it) }.map { it.toDTOFriend() }
    }

    fun validateLogin(loginRequest: LoginRequest): LoginResponse {
        val usuario = this.checkUsername(loginRequest.username)
        this.checkPassword(loginRequest.password, usuario!!.password)
        return LoginResponse(userID = usuario.id)
    }

    fun updateUserInfo(nuevoUsuario: UserInfoDTO): UserProfileDTO {
        val viejoUsuario = getByIdRaw(nuevoUsuario.id.toString())
        nuevoUsuario.nombre?.let { viejoUsuario.nombre = it }
        nuevoUsuario.apellido?.let { viejoUsuario.apellido = it }
        nuevoUsuario.alias?.let { viejoUsuario.alias = it }
        nuevoUsuario.fechaNacimiento?.let { viejoUsuario.fechaNacimiento = it }
        nuevoUsuario.email?.let { viejoUsuario.email = it }
        nuevoUsuario.perfil?.let { viejoUsuario.perfil = asignarPerfiles(it) }
        nuevoUsuario.tipoDeLector?.let { viejoUsuario.tipoDeLector = tipoDeLectorFactory(it) }
        userRepository.update(viejoUsuario)
        return viejoUsuario.toDTOProfile()
    }

    private fun tipoDeLectorFactory(tipo: String): TipoDeLector {
        return when (tipo) {
            Promedio.toString() -> Promedio
            Ansioso.toString() -> Ansioso
            Fanatico.toString() -> Fanatico
            Recurrente.toString() -> Recurrente
            else -> throw NotFoundException("El tipo de Lector seleccionado no existe")
        }
    }

    fun asignarPerfiles(perfiles: List<PerfilDeLecturaDTO>): PerfilDeUsuario {
        if (perfiles.size > 1) {
            return Combinador(perfiles.map { perfilBusquedaFactory(it) }.toMutableSet())
        }
        return perfilBusquedaFactory(perfiles.first())
    }

    private fun perfilBusquedaFactory(perfil: PerfilDeLecturaDTO): PerfilDeUsuario {
        return when (perfil.tipoPerfil) {
            Precavido.toString() -> Precavido
            Leedor.toString() -> Leedor
            Poliglota.toString() -> Poliglota
            Nativista.toString() -> Nativista
            Calculador(1.0, 2.0).toString() -> Calculador(perfil.rangoMin, perfil.rangoMax)
            Demandante.toString() -> Demandante
            Experimentado.toString() -> Experimentado
            Cambiante.toString() -> Cambiante
            else -> throw NotFoundException("El perfil de busqueda no existe")
        }
    }

    private fun checkUsername(username: String): Usuario? {
        val usuario: Usuario = this.findUsername(username) ?: throw BusinessException("USUARIO INCORRECTO")
        return usuario
    }

    private fun checkPassword(inputPassword: String, userPassword: String) {
        if (inputPassword != userPassword) {
            throw BusinessException("PASSWORD INCORRECTA")
        }
    }

    fun createAccount(newAccountRequest: CreateAccountRequest): CreateAccountResponse {
        this.checkAvaliableUsername(newAccountRequest.username)
        val newUser = this.newDefaultUser(newAccountRequest)
        userRepository.create(newUser)
        return CreateAccountResponse()
    }


    private fun checkAvaliableUsername(username: String) {
        val usuario: Usuario? = this.findUsername(username)
        //Si es Not Null, existe un Usuario con ese Username
        if (usuario != null) throw BusinessException("Nombre de usuario NO DISPONIBLE")
    }

    private fun findUsername(username: String): Usuario? {
        val usuario: Usuario? = this.userRepository.getAll().find {
            it.username == username
        }
        return usuario
    }

    private fun newDefaultUser(newAccountRequest: CreateAccountRequest): Usuario {
        return UsuarioBuilder()
            .username(newAccountRequest.username)
            .password(newAccountRequest.password)
            .nombre(newAccountRequest.name)
            .email(newAccountRequest.email)
            .alias(newAccountRequest.name)
            .lenguaje(Lenguaje.ESPANIOL)
            .build()
    }


}


