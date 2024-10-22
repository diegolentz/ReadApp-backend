package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.DTO.*
import ar.edu.unsam.algo3.mock.USERS
import ar.edu.unsam.algo3.mock.auxGenerarRecomendaciones
import ar.edu.unsam.algo3.mock.auxGenerarRecomendacionesAValorar
import excepciones.BusinessException
import excepciones.deletedRecommendation
import excepciones.recommendationAdded
import org.springframework.stereotype.Service


@Service
object ServiceRecommendation {
    private val recommendationRepository: Repositorio<Recomendacion> = Repositorio()
    var recomendaciones : MutableList<Recomendacion> = mutableListOf()
    init {
        auxGenerarRecomendaciones()
        auxGenerarRecomendacionesAValorar()
        USERS.forEach { user ->
            user.recomendaciones.forEach{
                recommendationRepository.create(it)
            }
        }
    }


    fun getAll(): List<RecommendationCardDTO> {
        recomendaciones = recommendationRepository.getAll().toMutableList()
        return recomendaciones.map { it: Recomendacion -> it.toCardDTO(ServiceUser.loggedUser) }.shuffled()
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


    fun updateRecommendation(recomendacionActualizada: RecomendacionEditarDTO): RecomendacionEditarDTO {
        val recomendacion = getById(recomendacionActualizada.id)
        recomendacion.actualizar(recomendacionActualizada)
        recommendationRepository.update(recomendacion)
        return recomendacion.editarDTO()
    }

    fun deleteRecommendation(recommendationID: Int): MessageResponse {
        val usuarioLogueado = ServiceUser.loggedUser
        val recommendation = recommendationRepository.getByID(recommendationID)
        recommendationRepository.delete(recommendation)
        usuarioLogueado.eliminarRecomendacion(recommendation)
        return MessageResponse(deletedRecommendation)
    }

    fun getWithFilter(filtro: String): List<RecommendationCardDTO> {
        recomendaciones = recommendationRepository.search(filtro).toMutableList()
        return recomendaciones.map { it: Recomendacion -> it.toCardDTO(ServiceUser.loggedUser) }
    }

    fun createValoracion(valoracionDTO: ValoracionDTO, id:Int): ValoracionDTO {
        var recomendacionAValorar = this.getById(id)
        var usuario = ServiceUser.loggedUser
        usuario.valorarRecomendacion(recomendacionAValorar,valoracionDTO.score,valoracionDTO.comentario)
        return valoracionDTO
    }

    fun getUserRecommendations(private: Boolean): List<RecommendationCardDTO> {
        val user = this.getLoggedUser()
        var userRecommendations = this.getLoggedUserRecommendations(user)
        var filteredRecommendations = this.filterRecommendationsByFlag(userRecommendations, private)
        return filteredRecommendations.map { it.toCardDTO(user) }
    }

    private fun getLoggedUser():Usuario{
        val loggedUserId = ServiceUser.loggedUserId.toString()
        return ServiceUser.getByIdRaw(loggedUserId)
    }

    private fun getLoggedUserRecommendations(user:Usuario):List<Recomendacion>{
        recomendaciones = recommendationRepository.getAll().toMutableList()
        return recomendaciones.filter {
            it.esCreador(user)
        }
    }
    private fun filterRecommendationsByFlag(recommendations:List<Recomendacion>,private:Boolean):List<Recomendacion>{
        return if(private){
            return recommendations.filter {
                !it.publica
            }
        }else{
            return recommendations
        }
    }

    fun getRecommendationsToValue():List<RecommendationCardDTO>{
        val loggedUser = ServiceUser.loggedUser
        val recommendations = loggedUser.recomendacionesAValorar
        return recommendations.map {
            it.toCardDTO(loggedUser)
        }
    }

    fun getRecommendationsByProfile():List<RecommendationCardDTO>{
        val loggedUser = ServiceUser.loggedUser
        val recommendations = recommendationRepository.getAll()
        val filteredRecommendations = recommendations.filter {
            loggedUser.perfil.recomendacionEsInteresante(it,loggedUser)
        }
        return filteredRecommendations.map {
            it.toCardDTO(loggedUser)
        }
    }
    fun addToValueLater(id:Int): MessageResponse{
        val loggedUser = ServiceUser.loggedUser
        val recommendation = recommendationRepository.getByID(id)
        loggedUser.agregarRecomendacionAValorar(recommendation)
        return MessageResponse(recommendationAdded)
    }
}

