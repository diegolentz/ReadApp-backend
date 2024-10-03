package ar.edu.unsam.algo3.dominio
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.features.Valoracion
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.observers.AgregarLibroObserver
import ar.edu.unsam.algo2.readapp.usuario.*
import java.time.LocalDate


class UserBasicDTO(
    val id:Int,
    var fotoPath:String,
    val nombre: String,
    val apellido:String,
    val alias: String,
    val lenguaje: Lenguaje,
    //Solo para vistas home, mis recomendaciones,
    val palabrasPorMinutos: Int,
)

class UserProfileDTO(
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
    var perfil: PerfilDeUsuario,
    var tipoDeLector: TipoDeLector,
    val amigos: MutableList<Usuario>,
    val librosLeidos: MutableList<Libro>,
    val librosALeer: MutableSet<Libro>,
    val recomendacionesAValorar: MutableList<Recomendacion>,
)

