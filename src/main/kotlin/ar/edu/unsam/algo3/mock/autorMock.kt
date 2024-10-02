package ar.edu.unsam.algo3.mock

import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje


val AUTOR = listOf(
    Autor(

        lenguaNativa = Lenguaje.RUSO,
        edad = 50,
        nombre = "Gabriel",
        apellido = "Garcia Marquez",
        seudonimo = "El Gabo"
    ),
    Autor(
        lenguaNativa = Lenguaje.INGLES,
        edad = 42,
        nombre = "J.K.",
        apellido = "Rowling",
        seudonimo = "Robert Galbraith"
    ),
    Autor(
        lenguaNativa = Lenguaje.ESPANIOL,
        edad = 67,
        nombre = "Isabel",
        apellido = "Allende",
        seudonimo = "La Novelista"
    ),
    Autor(
        lenguaNativa = Lenguaje.ALEMAN,
        edad = 55,
        nombre = "Franz",
        apellido = "Kafka",
        seudonimo = "El Existencialista"
    ),
    Autor(
        lenguaNativa = Lenguaje.FRANCES,
        edad = 48,
        nombre = "Marcel",
        apellido = "Proust",
        seudonimo = "El Memorialista"
    )
)