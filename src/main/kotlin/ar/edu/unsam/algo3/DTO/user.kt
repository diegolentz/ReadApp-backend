package ar.edu.unsam.algo3.DTO
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
<<<<<<< HEAD:src/main/kotlin/ar/edu/unsam/algo3/DTO/user.kt
import ar.edu.unsam.algo2.readapp.usuario.*
=======
import ar.edu.unsam.algo2.readapp.usuario.Usuario
>>>>>>> development:src/main/kotlin/ar/edu/unsam/algo3/dominio/user.kt
import java.time.LocalDate


data class UserBasicDTO(
    val id:Int,
    var fotoPath:String,
    val nombre: String,
    val apellido:String,
    val alias: String,
    val lenguaje: Lenguaje,
    //Solo para vistas home, mis recomendaciones,
    val palabrasPorMinutos: Int,
)

fun Usuario.toDTOBasic() = UserBasicDTO(
    id = id,
    fotoPath = fotoPath,
    nombre = nombre,
    apellido = apellido,
    alias = alias,
    lenguaje = lenguaje,
    palabrasPorMinutos = palabrasPorMinutos
)

data class UserProfileDTO(
    var id: Int,
    var fotoPath:String,
    var nombre: String,
    var apellido: String,
    var alias: String,
    var lenguaje: Lenguaje,
    var palabrasPorMinutos: Int,
    //Exclusivo de perfil de usuario
    var fechaNacimiento: LocalDate,
    var email: String,
    var perfil: List<String>,
    var tipoDeLector: String,
    val amigos: MutableList<Usuario>,
    val librosLeidos: MutableList<Libro>,
    val librosALeer: MutableSet<Libro>,
    val recomendacionesAValorar: MutableList<Recomendacion>,
)

fun Usuario.toDTOProfile() = UserProfileDTO(
    id = id,
    fotoPath = fotoPath,
    nombre = nombre,
    apellido =  apellido,
    alias = alias,
    lenguaje = lenguaje,
    palabrasPorMinutos = palabrasPorMinutos,
    fechaNacimiento = fechaNacimiento,
    email = direccionMail,
    perfil =  perfil.toList(),
    tipoDeLector = tipoDeLector.toString(),
    amigos = amigos,
    librosLeidos = librosLeidos,
    librosALeer = librosALeer,
    recomendacionesAValorar = recomendacionesAValorar
)

data class LoginRequest(
    var username:String = "",
    var password:String = ""
)

data class LoginResponse(
    var userID:Int = -1
)

data class CreateAccountRequest(
    var username:String = "",
    var password:String = "",
    var name:String = "",
    var email:String = ""
)

data class CreateAccountResponse(
    var userID:Int = -1
)