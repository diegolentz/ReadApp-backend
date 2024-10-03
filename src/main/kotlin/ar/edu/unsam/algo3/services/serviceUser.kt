package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.mock.USERS
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
//        usuarios[0].leer(libros[10])
//        usuarios[0].leer(libros[11])
  }

    fun getAll(): List<Usuario> = userRepository.getAll().toList()

    fun getById(userID: Int): Usuario = userRepository.getByID(userID)
//    fun createRecommendation(recommendation: Recomendacion): Recomendacion {
//        recommendationRepository.create(recommendation)
//        return this.getById(recommendation.id)
//    }
//

//
//    fun updateRecommendation(recommendation: Recomendacion): Recomendacion {
//        recommendationRepository.update(recommendation)
//        return getById(recommendation.id)
//    }
//
//    fun deleteRecommendation(recommendationID: Int): Recomendacion {
//        val recommendation = getById(recommendationID)
//        recommendationRepository.delete(recommendation)
//        return recommendation
//    }

}

