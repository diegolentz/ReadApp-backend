package ar.edu.unsam.algo3.services

import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.*
import ar.edu.unsam.algo3.DTO.*
import ar.edu.unsam.algo3.mock.PHOTOS_PATH
import ar.edu.unsam.algo3.mock.USERS
import ar.edu.unsam.algo3.mock.auxGenerarAmistades
import ar.edu.unsam.algo3.mock.auxGenerarRecomendaciones
import excepciones.*
import org.springframework.stereotype.Service


@Service
object ServiceUser {
    private val userRepository: Repositorio<Usuario> = Repositorio()
    var loggedUserId:Int = 5
    lateinit var loggedUser:Usuario
    init {
        auxGenerarAmistades()
        USERS.forEach { user ->
            userRepository.create(user)
        }
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

    fun getByIdFriends(idTypeString: Int, muestroAmigos: Boolean): List<UserFriendDTO> {
        val usuarioLogueado = this.getByIdRaw(idTypeString.toString())
        if (muestroAmigos) return usuarioLogueado.amigos.map { it.toDTOFriend() }
        else {
            val usuarios = this.getAll().toMutableList()
            usuarios.remove(usuarioLogueado)
            return this.notMyFriends(usuarios, usuarioLogueado)
        }
    }

    private fun notMyFriends(usuarios: List<Usuario>, usuarioLogueado: Usuario): List<UserFriendDTO> {
        return usuarios.filter { !usuarioLogueado.esAmigoDe(it) }.map { it.toDTOFriend() }
    }

    fun validateLogin(loginRequest: LoginRequest): LoginResponse {
        val usuario = this.findUsername(loginRequest.username) ?: throw NotFoundException(loginErrorMessage)
        this.checkPassword(loginRequest.password, usuario!!.password)
        val response = LoginResponse(userID = usuario.id)
        this.loggedUserId = response.userID
        this.loggedUser = this.getByIdRaw(loggedUserId.toString())
        return response
    }

    fun updateUserInfo(nuevoUsuario: UserInfoDTO): UserProfileDTO {
        val viejoUsuario = getByIdRaw(nuevoUsuario.id.toString())
        nuevoUsuario.nombre?.let { viejoUsuario.nombre = it }
        nuevoUsuario.apellido?.let { viejoUsuario.apellido = it }
        nuevoUsuario.username?.let { viejoUsuario.username = it }
        nuevoUsuario.fechaNacimiento?.let { viejoUsuario.fechaNacimiento = it }
        nuevoUsuario.email?.let { viejoUsuario.email = it }
        nuevoUsuario.perfil?.let { viejoUsuario.perfil = asignarPerfiles(it) }
        nuevoUsuario.tipoDeLector?.let { viejoUsuario.tipoDeLector = tipoDeLectorFactory(it) }
        userRepository.update(viejoUsuario)
        return viejoUsuario.toDTOProfile()
    }

    fun updateAmigos(body: UpdateFriendsMessage) {
        val amigosAModificarIds = body.amigosAModificar.map { amigo -> getByIdRaw(amigo) }
        val usuarioLogueado = this.getByIdRaw(body.id.toString())

        if (body.agregarAmigos) amigosAModificarIds.map { user -> usuarioLogueado.agregarAmigo(user) }
        else amigosAModificarIds.map {user -> usuarioLogueado.eliminarAmigo(user)}
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

    private fun checkPassword(inputPassword: String, userPassword: String) {
        if (inputPassword != userPassword) {
            throw throw NotFoundException(loginErrorMessage)
        }
    }

    fun createAccount(newAccountRequest: CreateAccountRequest): CreateAccountResponse {
        this.checkAvaliableEmail(newAccountRequest.email)
        this.checkAvaliableUsername(newAccountRequest.username)
        val newUser = this.newDefaultUser(newAccountRequest)
        userRepository.create(newUser)
        return CreateAccountResponse()
    }


    private fun checkAvaliableUsername(username: String) {
        val usuario: Usuario? = this.findUsername(username)
        //Si es Not Null, existe un Usuario con ese Username
        if (usuario != null) throw BadRequestException(usernameInvalidMessage)
    }
    private fun checkAvaliableEmail(email: String) {
        val usuario: Usuario? = this.findEmail(email)
        //Si es Not Null, existe un Usuario con ese Username
        if (usuario != null) throw BadRequestException(emailInvalidMessage)
    }
    private fun findUsername(username: String): Usuario? {
        val usuario: Usuario? = this.userRepository.getAll().find {
            it.username == username
        }
        return usuario
    }

    private fun findEmail(email: String): Usuario? {
        val usuario: Usuario? = this.userRepository.getAll().find {
            it.email == email
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
            .fotoPath(PHOTOS_PATH[0])
            .build()
            .apply {
                perfil = Leedor
                tipoDeLector = Promedio
            }
    }

    fun passwordRecover(passwordRecoveryRequest: PasswordRecoveryRequest): MessageResponse {
        val username:String = passwordRecoveryRequest.username
        val email:String = passwordRecoveryRequest.email
        val newPassword:String = passwordRecoveryRequest.newPassword

        val usuario: Usuario = this.findUsername(username) ?: throw NotFoundException(passwordRecoveryErrorMessage)
        this.checkEmail(email, usuario!!.email)
        usuario.password = newPassword
        return MessageResponse(passwordChangeOK)
    }

    private fun checkEmail(requestEmail:String, userEmail:String){
        if (requestEmail != userEmail) {
            throw throw NotFoundException(passwordRecoveryErrorMessage)
        }
    }

}


