package ar.edu.unsam.algo3.mock

import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import java.time.LocalDate


val USERS = listOf(
    Usuario(
        photo = "",
        nombre = "Inosuke",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        photo = "",
        nombre = "Tanjiro",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        photo = "",
        nombre = "Momonosuke",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        photo = "",
        nombre = "Saitama",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        photo = "",
        nombre = "Ronoroa",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ),
    Usuario(
        photo = "",
        nombre = "Kinemon",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    )
)


