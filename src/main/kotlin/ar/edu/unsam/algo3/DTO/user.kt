package ar.edu.unsam.algo3.DTO

import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.Combinador
import ar.edu.unsam.algo2.readapp.usuario.PerfilDeUsuario
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.services.ServiceUser
import java.time.LocalDate
import kotlin.reflect.typeOf


data class UserBasicDTO(
    val id: Int,
    var fotoPath: String,
    val nombre: String,
    val apellido: String,
    val username: String,
    val lenguaje: Lenguaje,
    //Solo para vistas home, mis recomendaciones,
    val palabrasPorMinutos: Int,
)

fun Usuario.toDTOBasic() = UserBasicDTO(
    id = id,
    fotoPath = fotoPath,
    nombre = nombre,
    apellido = apellido,
    username = username,
    lenguaje = lenguaje,
    palabrasPorMinutos = palabrasPorMinutos
)

data class UserProfileDTO(
    var id: Int,
    var fotoPath: String,
    var nombre: String,
    var apellido: String,
    var username: String,
    var lenguaje: Lenguaje,
    var palabrasPorMinutos: Int,
    var fechaNacimiento: LocalDate,
    var email: String,
    var perfil: List<Any>,
    var tipoDeLector: String,
    val tiempoLecturaPromedio:Double
)

fun Usuario.toDTOProfile() = UserProfileDTO(
    id = id,
    fotoPath = fotoPath,
    nombre = nombre,
    apellido = apellido,
    username = username,
    lenguaje = lenguaje,
    palabrasPorMinutos = palabrasPorMinutos,
    fechaNacimiento = fechaNacimiento,
    email = email,
    perfil = perfil.toList(),
    tipoDeLector = tipoDeLector.toString(),
    tiempoLecturaPromedio = this.tiempoLecturaPromedio()
)

data class  UserInfoDTO(
    var id: Int?,
    var nombre: String?,
    var apellido: String?,
    var username: String?,
    var fechaNacimiento: LocalDate?,
    var email: String?,
    var perfil: List<PerfilDeLecturaDTO>?,
    var tipoDeLector: String?,
)


data class PerfilDeLecturaDTO(
    var tipoPerfil: String,
    var rangoMin: Double,
    var rangoMax: Double
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

//fun Usuario.toDTOProfileFriends() = UserProfileFriendsDTO(
//    id = id,
//    fotoPath = fotoPath,
//    nombre = nombre,
//    apellido = apellido,
//    amigos = amigos.map { it.toDTOFriend() } )

//data class UserProfileFriendsDTO(
//    var id: Int?,
//    var fotoPath: String,
//   var nombre: String,
//    var apellido: String,
//    var amigos: List<UserFriendDTO>
//)

fun Usuario.toDTOFriend() = UserFriendDTO(
    id = id,
    fotoPath = fotoPath,
    nombreCompleto = "$nombre $apellido",
    username = username
)

data class UserFriendDTO(
    var id: Int?,
    var fotoPath: String,
    var nombreCompleto: String,
    var username: String
)

fun Usuario.toDTOProfileAside() = UserProfileAsideDTO(
    id = id,
    fotoPath = fotoPath,
    nombreCompleto = "$nombre $apellido"
)

data class UserProfileAsideDTO(
    var id: Int?,
    var fotoPath: String,
    var nombreCompleto: String
)

data class PasswordRecoveryRequest(
    var email: String = "",
    var username: String = "",
    var newPassword: String = ""
)

data class PasswordRecoveryResponse(
    var message: String = "Contrasenia cambiada cone exito!"
)

data class MessageResponse(
    var message:String
)

data class UpdateFriendsMessage(
    var id: Int,
    var amigosAModificar: List<String>,
    var agregarAmigos: Boolean
)