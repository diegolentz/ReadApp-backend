package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo3.mock.RECOMMENDATIONS
import org.springframework.stereotype.Service


@Service
object ServiceRecommendation {
    private val recommendationRepository: Repositorio<Recomendacion> = Repositorio()
    init {
        RECOMMENDATIONS.forEach { recommendation ->
            recommendationRepository.create(recommendation)
        }
    }

    fun getAll(): List<Recomendacion> = recommendationRepository.getAll().toList()

    fun createRecommendation(recommendation: Recomendacion): Recomendacion {
        recommendationRepository.create(recommendation)
        return this.getById(recommendation.id)
    }

    fun getById(recommendationID: Int): Recomendacion = recommendationRepository.getByID(recommendationID)

    fun updateRecommendation(recommendation: Recomendacion): Recomendacion {
        recommendationRepository.update(recommendation)
        return getById(recommendation.id)
    }

    fun deleteRecommendation(recommendationID: Int): Recomendacion {
        val recommendation = getById(recommendationID)
        recommendationRepository.delete(recommendation)
        return recommendation
    }

}

