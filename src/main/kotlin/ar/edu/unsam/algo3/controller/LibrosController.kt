package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceLibros
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LibrosController(val serviceLibros: ServiceLibros) {

    @GetMapping("/libros")
    fun obtenerLibros(): List<Libro> = serviceLibros.get()

    @GetMapping("/libros/{id}")
    fun libroPorId(@PathVariable id: Int) = serviceLibros.getById(id)

    @PostMapping("/libros")
    fun crearLibro(@RequestBody libroBody: Libro): Libro = serviceLibros.nuevoLibro(libroBody)

    @PutMapping("/libros")
    fun actualizarLibro(@RequestBody nuevoLibro: Libro): Libro {
        serviceLibros.actualizarLibro(nuevoLibro)
        return serviceLibros.getById(nuevoLibro.id)
    }


}