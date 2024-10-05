package ar.edu.unsam.algo3.mock

import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import java.time.LocalDate


val USERS = listOf(
    Usuario(
        fotoPath = "inosuke.jpeg",
        nombre = "Inosuke",
        alias = "elInosuke",
        apellido = "Hashibira",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",

        lenguaje = Lenguaje.entries.random()
    ).apply {
        username = "diego"
        password = "diego"
    },
    Usuario(
        fotoPath = "dwightSchrute.jpeg",
        nombre = "Dwight Schrute",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ).apply {
        username = "valen"
        password = "valen"
    },

    Usuario(
        fotoPath = "ippo.jpeg",
        nombre = "Mamoru Takamura",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ).apply {
        username = "delfi"
        password = "delfi"
    },

    Usuario(
        fotoPath = "kellyKapoor.jpeg",
        nombre = "Kelly Kapoor",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ).apply {
        username = "pica"
        password = "pica"
    },

    Usuario(
        fotoPath = "kevinMalone.jpeg",
        nombre = "Kevin Malone",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.entries.random()
    ).apply {
        username = "adrian"
        password = "adrian"
    },
    Usuario(
        fotoPath = "",
        nombre = "Kinemon",
        alias = "",
        fechaNacimiento = LocalDate.now(),
        apellido = "",
        palabrasPorMinutos = (5..250).random(),
        direccionMail = "",
        lenguaje = Lenguaje.values().random()
    ).apply {
        username = "admin"
        password = "admin"
    }

)


