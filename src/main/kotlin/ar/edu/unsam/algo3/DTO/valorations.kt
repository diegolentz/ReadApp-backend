package ar.edu.unsam.algo3.DTO

import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.mock.authors
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class ValoracionDTO (
    var author: String,
    var fotoPath: String,
    var score: Int,

    @JsonDeserialize(using = LocalDateDeserializer::class)
    var fecha: LocalDate,

    var comentario: String
)

fun Valoracion.toDTO() = ValoracionDTO(
    author = autor.nombreApellido(autor),
    fotoPath = autor.fotoPath,
    score = valor,
    fecha = fecha,
    comentario = comentario
)

fun Valoracion.fromDTO(valoracionDTO: ValoracionDTO): Valoracion{
    return Valoracion(
        valor = valoracionDTO.score,
        comentario = valoracionDTO.comentario,
        autor = Usuario( nombre = "Prueba")
    )
}

class LocalDateDeserializer : JsonDeserializer<LocalDate>() {
    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate {
        return LocalDate.parse(p.text, formatter)
    }
}