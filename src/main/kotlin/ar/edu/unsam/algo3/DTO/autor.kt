package ar.edu.unsam.algo3.DTO

import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import com.jayway.jsonpath.internal.function.text.Length

class AutorDTO(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val nacionalidad : Lenguaje,
    val creadorLibros: Boolean

)

class AuthorEditDTO (
    val id: Int,
    val nombre: String,
    val apellido: String,
    val nacionalidad : Lenguaje,
    val lenguaje: Array<Lenguaje>

)

class AutorCreateDTO (
    var lenguaNativa: Lenguaje ,
    var edad: Int ,
    var apellido: String ,
    var nombre: String ,
    var seudonimo: String
)