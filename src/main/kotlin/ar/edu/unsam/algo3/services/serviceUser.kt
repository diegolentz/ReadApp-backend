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
    }

    fun getAll(): List<Usuario> = userRepository.getAll().toList()

//    fun createRecommendation(recommendation: Recomendacion): Recomendacion {
//        recommendationRepository.create(recommendation)
//        return this.getById(recommendation.id)
//    }
//
//    fun getById(recommendationID: Int): Recomendacion = recommendationRepository.getByID(recommendationID)
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

