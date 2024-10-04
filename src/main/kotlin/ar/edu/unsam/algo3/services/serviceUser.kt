package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.dominio.UserBasicDTO
import ar.edu.unsam.algo3.dominio.UserProfileDTO
import ar.edu.unsam.algo3.dominio.toDTOBasic
import ar.edu.unsam.algo3.dominio.toDTOProfile
import ar.edu.unsam.algo3.mock.USERS
import excepciones.BusinessException
import org.springframework.stereotype.Service


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

}

