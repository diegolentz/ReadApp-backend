package ar.edu.unsam.algo3.DTO

import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import java.time.LocalDate


data class UserBasicDTO(
    val id: Int,
    var fotoPath: String,
    val nombre: String,
    val apellido: String,
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
    var fotoPath: String,
    var nombre: String,
    var apellido: String,
    var alias: String,
    var lenguaje: Lenguaje,
    var palabrasPorMinutos: Int,
    //Exclusivo de perfil de usuario
    var fechaNacimiento: LocalDate,
    var email: String,
    var perfil: List<Any>,
    var tipoDeLector: String,
    val amigos: MutableList<Usuario>,
    val librosLeidos: MutableList<Libro>,
    val librosALeer: MutableSet<Libro>,
    val recomendacionesAValorar: MutableList<Recomendacion>,
    val tiempoLecturaPromedio:Double
)

fun Usuario.toDTOProfile() = UserProfileDTO(
    id = id,
    fotoPath = fotoPath,
    nombre = nombre,
    apellido = apellido,
    alias = alias,
    lenguaje = lenguaje,
    palabrasPorMinutos = palabrasPorMinutos,
    fechaNacimiento = fechaNacimiento,
    email = direccionMail,
    perfil = perfil.toList(),
    tipoDeLector = tipoDeLector.toString(),
    amigos = amigos,
    librosLeidos = librosLeidos,
    librosALeer = librosALeer,
    recomendacionesAValorar = recomendacionesAValorar,
    tiempoLecturaPromedio = this.tiempoLecturaPromedio()
)

data class UserInfoDTO(
    var id: Int?,
    var nombre: String?,
    var apellido: String?,
    var alias: String?,
    var fechaNacimiento: LocalDate?,
    var email: String?,
    var perfil: List<PerfilDeLecturaDTO>?,
    var tipoDeLector: String?,
)

data class PerfilDeLecturaDTO(
    var tipoPerfil: String,
    var rangoMin: Double = 0.0,
    var rangoMax: Double = 0.0
)

data class LoginRequest(
    var username: String = "",
    var password: String = ""
)

data class LoginResponse(
    var userID: Int = -1
)

data class CreateAccountRequest(
    var username: String = "",
    var password: String = "",
    var name: String = "",
    var email: String = ""
)

data class CreateAccountResponse(
    var message: String = "Cuenta creada con exito"
)

fun Usuario.toDTOProfileFriends() = UserProfileFriendsDTO(
    id = id,
    fotoPath = fotoPath,
    nombre = nombre,
    apellido = apellido,
    amigos = amigos.map { it.toDTOFriend() }
)

data class UserProfileFriendsDTO(
    var id: Int?,
    var fotoPath: String,
    var nombre: String,
    var apellido: String,
    var amigos: List<UserFriendDTO>
)

fun Usuario.toDTOFriend() = UserFriendDTO(
    id = id,
    fotoPath = fotoPath,
    nombre = nombre,
    apellido = apellido,
    alias = alias
)

data class UserFriendDTO(
    var id: Int?,
    var fotoPath: String,
    var nombre: String,
    var apellido: String,
    var alias: String
)
