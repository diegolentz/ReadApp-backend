package ar.edu.unsam.algo3.DTO
import LibroDTO
import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.libro.Libro


class RecomendacionDTO(
    val creador: String,
    val librosRecomendados: MutableSet<LibroDTO>,
    val titulo: String,
    val contenido: String,
    var publica: Boolean,
    val valoraciones: MutableSet<ValoracionDTO>,
    var id : Int
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

