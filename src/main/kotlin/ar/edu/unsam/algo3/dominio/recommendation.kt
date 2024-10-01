package ar.edu.unsam.algo3.dominio
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario


class RecommendationDTO(
    val creador: String,
    val librosRecomendados: MutableSet<Libro>,
    val titulo: String,
    val contenido: String,
    var publica: Boolean,
    val valoraciones: MutableSet<Valoracion>
) {
//    fun convertir(): Recomendacion =
//        Recomendacion(
//            creador = creador,
//            librosRecomendados = librosRecomendados,
//            titulo = titulo,
//            contenido = contenido,
//            publica = publica,
//            valoraciones = valoraciones
//        )
}

