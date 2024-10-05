package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo3.DTO.RecomendacionDTO

import ar.edu.unsam.algo3.mock.RECOMMENDATIONS
import org.springframework.stereotype.Service


@Service
object ServiceRecommendation {
    private val recommendationRepository: Repositorio<Recomendacion> = Repositorio()
    var recomendaciones : MutableList<Recomendacion> = mutableListOf()
    init {
        RECOMMENDATIONS.forEach { recommendation ->
            recommendationRepository.create(recommendation)
        }
    }

    fun getAll(): List<RecomendacionDTO> {
        recomendaciones = recommendationRepository.getAll().toMutableList()
        return recomendaciones.map { it : Recomendacion -> it.toDTO() }
    }

    fun createRecommendation(recommendation: Recomendacion): Recomendacion {
        recommendationRepository.create(recommendation)
        return this.getById(recommendation.id)
    }

    fun getByIdDTO (recommendationID: Int): RecomendacionDTO {
        return recommendationRepository.getByID(recommendationID).toDTO()
    }

    fun getById (recommendationID: Int): Recomendacion =
        recommendationRepository.getByID(recommendationID)


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

