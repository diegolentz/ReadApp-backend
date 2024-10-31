package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.DTO.*
import excepciones.deletedRecommendation
import excepciones.recommendationAdded
import org.springframework.stereotype.Service


@Service
object ServiceRecommendation {
    val recommendationRepository: Repositorio<Recomendacion> = Repositorio()
    var recomendaciones : MutableList<Recomendacion> = mutableListOf()
//    init {
//        auxGenerarRecomendaciones()
//        auxGenerarRecomendacionesAValorar()
//        USERS.forEach { user ->
//            user.recomendaciones.forEach{
//                recommendationRepository.create(it)
//            }
//        }
//    }

    fun getAllRaw() : MutableList<Recomendacion> = recommendationRepository.getAll().toMutableList()

    fun getAllSize() :Int = getAllRaw().size


    fun getAll(): List<RecommendationCardDTO> {
        recomendaciones = getAllRaw()
        return recomendaciones.map { it: Recomendacion -> it.toCardDTO(ServiceUser.loggedUser) }.shuffled()
    }

    fun createRecommendation(recomendacionCrearDTO: RecomendacionCrearDTO): RecomendacionDTO {
        val user = ServiceUser.loggedUser
        val nuevaRecomendacion = Recomendacion(Usuario()).fromCreateJSON(recomendacionCrearDTO)
        user.crearRecomendacion(
            titulo = nuevaRecomendacion.titulo,
            librosParaRecomendar = nuevaRecomendacion.librosRecomendados,
            contenido = nuevaRecomendacion.contenido,
            publico = nuevaRecomendacion.publica
        )
        recommendationRepository.create(nuevaRecomendacion)
        return nuevaRecomendacion.toDTO()
    }

    fun getByIdDTO (recommendationID: Int): RecomendacionDTO {
        this.valorationUser(recommendationID)
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
        val interestingRecommendations = this.getInterestingRecommendations()
        val filteredRecommendations = interestingRecommendations.filter {
            it.cumpleCriterioBusqueda(filtro)
        }
        return filteredRecommendations.map { it: Recomendacion -> it.toCardDTO(ServiceUser.loggedUser) }
    }

    fun createValoracion(valoracionDTO: ValoracionDTO, id:Int): ValoracionDTO {
        val recomendacionAValorar = this.getById(id)
        val usuario = ServiceUser.loggedUser
        usuario.valorarRecomendacion(recomendacionAValorar,valoracionDTO.score,valoracionDTO.comentario)
        if(usuario.recomendacionesAValorar.contains(recomendacionAValorar)){
            usuario.recomendacionesAValorar.remove(recomendacionAValorar)
        }
        return valoracionDTO
    }

    fun getUserRecommendations(private: Boolean): List<RecommendationCardDTO> {
        val user = this.getLoggedUser()
        val userRecommendations = this.getLoggedUserRecommendations(user)
        val filteredRecommendations = this.filterRecommendationsByFlag(userRecommendations, private)
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
        val interestingRecommendations = this.getInterestingRecommendations()
        return interestingRecommendations.map {
            it.toCardDTO(ServiceUser.loggedUser)
        }
    }

    fun addToValueLater(id:Int): MessageResponse{
        val loggedUser = ServiceUser.loggedUser
        val recommendation = recommendationRepository.getByID(id)
        loggedUser.agregarRecomendacionAValorar(recommendation)
        return MessageResponse(recommendationAdded)
    }

    fun valorationUser(recommendationID: Int){
        val recomendacion = this.getById(recommendationID)
        val puedeValorar = ServiceUser.loggedUser.puedeCrearValoracion(recomendacion)
        recomendacion.puedeValorar = puedeValorar
    }


    fun getInterestingRecommendations(): List<Recomendacion>{
        val loggedUser = ServiceUser.loggedUser
        val recommendations = recommendationRepository.getAll()
        val filteredRecommendations = recommendations.filter {
            loggedUser.perfil.recomendacionEsInteresante(it,loggedUser)
        }
        return filteredRecommendations
    }

    private fun filterInterestingRecommendations(recommendations:List<Recomendacion>): List<Recomendacion>{
        val loggedUser = ServiceUser.loggedUser
        return recommendations.filter {
            loggedUser.perfil.recomendacionEsInteresante(it, loggedUser)
        }
    }
}

