package ar.edu.unsam.algo3.DTO

import LibroDTO
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.services.ServiceUser


class RecomendacionDTO(
    val creador: String,
    val librosRecomendados: MutableSet<LibroDTO>,
    val titulo: String,
    val contenido: String,
    var publica: Boolean,
    val valoraciones: MutableSet<ValoracionDTO>,
    val valoracionTotal: Int,
    var id: Int,
    var puedeValorar: Boolean
)

class RecomendacionCrearDTO(
    val titulo: String,
    val librosRecomendados: MutableSet<LibroDTO>,
    val contenido: String,
    var publica: Boolean,
)

class RecomendacionEditarDTO(
    val titulo: String,
    val librosRecomendados: MutableSet<LibroDTO>,
    val contenido: String,
    val publica: Boolean,
    var id: Int
)

data class RecommendationCardDTO(
    var id: Int,
    val title: String,
    val canEdit: Boolean,
    val canDelete: Boolean,
    var canAccess: Boolean,
    var canValorate:Boolean,
    var isPending: Boolean,
    val content: String,
    var bookTitles: List<String>,
    val popularity: Int,
    val aproxTime: Double,
)

fun Recomendacion.toCardDTO(user:Usuario) = RecommendationCardDTO(
    id = id,
    title = titulo,
    canEdit = this.esEditablePor(user, this.librosRecomendados),
    canDelete = this.esCreador(user),
    canAccess = this.esAccesiblePara(user),
    canValorate = !this.fueValoradaPor(user) && this.puedeDejarValoracion(user),
    isPending = user.recomendacionesAValorar.contains(this),
    content = contenido,
    bookTitles = librosRecomendados.map { it.titulo }.toList(),
    popularity = this.valoracionPromedio(),
    aproxTime = this.tiempodeLectura(user),
)

fun Recomendacion.fromCreateJSON(RecomendacionCrearDTO: RecomendacionCrearDTO) : Recomendacion =
    Recomendacion(
        creador = ServiceUser.loggedUser,
        titulo = RecomendacionCrearDTO.titulo,
        librosRecomendados = librosRecomendados.toMutableSet(),
        contenido = RecomendacionCrearDTO.contenido,
        publica = RecomendacionCrearDTO.publica
    )