package ar.edu.unsam.algo3.dominio
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario


class RecommendationDTO(
    val creador: Usuario,
    val librosRecomendados: MutableSet<Libro> = mutableSetOf(),
    val titulo: String = "",
    val contenido: String = "",
    var publica: Boolean = true,
    val valoraciones: MutableSet<Valoracion> = mutableSetOf()
) {
    fun convertir(): Recomendacion =
        Recomendacion(
            creador = creador,
            librosRecomendados = librosRecomendados,
            titulo = titulo,
            contenido = contenido,
            publica = publica,
            valoraciones = valoraciones
        )
}
