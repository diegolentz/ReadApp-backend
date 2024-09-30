package ar.edu.unsam.algo3.dominio
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import java.time.LocalDate


class UserDTO(
    var photo:String = "",
    var nombre: String = "",
    var apellido: String = "",
    var alias: String = "",
    var fechaNacimiento: LocalDate = LocalDate.now(),
    var palabrasPorMinutos: Int = (5..250).random(),
    var direccionMail: String = "",
    var lenguaje: Lenguaje = Lenguaje.values().random()
) {

}
