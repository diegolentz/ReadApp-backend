package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.DTO.*
import ar.edu.unsam.algo3.mock.USERS
import excepciones.BusinessException
import org.springframework.stereotype.Service
import kotlin.random.Random


@Service
object ServiceUser {
    private val userRepository: Repositorio<Usuario> = Repositorio()
    init {
        USERS.forEach { user ->
            userRepository.create(user)
        }

        var libros = ServiceLibros.get()

        val usuarios =   this.getAll()

        usuarios[0].agregarLibroALeer(libros[0])
        usuarios[0].agregarLibroALeer(libros[1])
        usuarios[0].agregarLibroALeer(libros[2])
        usuarios[0].agregarLibroALeer(libros[3])
        usuarios[0].agregarLibroALeer(libros[4])
        usuarios[0].agregarLibroALeer(libros[5])

        usuarios[0].leer(libros[6])
        usuarios[0].leer(libros[7])
        usuarios[0].leer(libros[8])
        usuarios[0].leer(libros[9])

  }

    fun getAll(): List<Usuario> = userRepository.getAll().toList()

    fun getByIdRaw(idTypeString: String): Usuario{
        val usuario:Usuario?
        try {
            val idTypeNumber = Integer.valueOf(idTypeString)
            usuario = userRepository.getByID(idTypeNumber)
        }catch (err:NumberFormatException){
            throw BusinessException("$idTypeString no es un entero")
        }
        return usuario
    }
    fun getByIdBasic(idTypeString: String): UserBasicDTO{
        return this.getByIdRaw(idTypeString).toDTOBasic()
    }

    fun getByIdProfile(idTypeString: String):UserProfileDTO{
        return this.getByIdRaw(idTypeString).toDTOProfile()
    }
    fun validateLogin(loginRequest: LoginRequest): LoginResponse{
        val usuario = this.checkUsername(loginRequest.username)
        this.checkPassword(loginRequest.password, usuario!!.password)
        return LoginResponse(userID = usuario.id)
    }
    private fun checkUsername(username:String):Usuario?{
        val usuario: Usuario = this.findUsername(username) ?: throw BusinessException("USUARIO INCORRECTO")
        return usuario
    }
    private fun checkPassword(inputPassword:String, userPassword:String){
        if(inputPassword != userPassword){
            throw BusinessException("PASSWORD INCORRECTA")
        }
    }

    fun createAccount(newAccountRequest: CreateAccountRequest): CreateAccountResponse{
        this.checkAvaliableUsername(newAccountRequest.username)
        val newUser = this.newDefaultUser(newAccountRequest)
        userRepository.create(newUser)
        return CreateAccountResponse(userID = newUser.id)
    }

    private fun checkAvaliableUsername(username: String){
        val usuario:Usuario? = this.findUsername(username)
        //Si es Not Null, existe un Usuario con ese Username
        if(usuario != null) throw BusinessException("Nombre de usuario NO DISPONIBLE")
    }
    private fun findUsername(username:String):Usuario?{
        val usuario:Usuario? = this.userRepository.getAll().find {
            it.username == username
        }
        return usuario
    }

    private fun newDefaultUser(newAccountRequest: CreateAccountRequest):Usuario{
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


