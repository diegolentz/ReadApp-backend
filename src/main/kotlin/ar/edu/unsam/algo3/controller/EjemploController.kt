package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceLibros
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EjemploController(val serviceLibros: ServiceLibros) {

    @GetMapping("/libros")
    fun obtenerLibros() : List<Libro> = serviceLibros.get()

    @PostMapping("/crearLibro")
    fun crearLibros(@RequestBody libroBody:Libro) : Libro = serviceLibros.nuevoLibro(libroBody)

}