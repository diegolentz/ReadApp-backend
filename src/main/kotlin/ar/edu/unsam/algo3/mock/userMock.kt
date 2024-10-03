package ar.edu.unsam.algo3.mock

import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import java.time.LocalDate


val USERS = listOf(
    Usuario(
        fotoPath = "inosuke.jpeg",
        nombre = "Inosuke",
        alias = "elInosuke",
        fechaNacimiento = LocalDate.now(),
        apellido = "Hashibira",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        fotoPath = "",
        nombre = "Tanjiro",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        fotoPath = "",
        nombre = "Momonosuke",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        fotoPath = "",
        nombre = "Saitama",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        fotoPath = "",
        nombre = "Ronoroa",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        fotoPath = "",
        nombre = "Kinemon",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    )
)


