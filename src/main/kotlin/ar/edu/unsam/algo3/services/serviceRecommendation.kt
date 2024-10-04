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

    fun updateRecommendation(recomendacionActualizada: Recomendacion,id:Int): Recomendacion {

        var recomendacion = getById(id)
        recomendacion.actualizar(recomendacionActualizada)
        recommendationRepository.update(recomendacion)
        return recomendacion
    }

    fun deleteRecommendation(recommendationID: Int): Recomendacion {
        val recommendation = getById(recommendationID)
        recommendationRepository.delete(recommendation)
        return recommendation
    }

}

