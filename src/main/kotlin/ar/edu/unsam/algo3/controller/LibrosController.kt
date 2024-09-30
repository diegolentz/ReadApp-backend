package ar.edu.unsam.algo3.controller

import LibroBuilder
import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceLibros

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("http://localhost:4200"))
@RestController

class LibrosController(val serviceLibros: ServiceLibros) {

    @GetMapping("/libros")
    fun obtenerLibros(): List<Libro> = serviceLibros.get()

    @GetMapping("/libros/{id}")
    fun libroPorId(@PathVariable id: Int) = serviceLibros.getById(id)

    @PostMapping("/libros")
    fun crearLibro(@RequestBody libroBody: LibroDTO): Libro = serviceLibros.nuevoLibro(libroBody.convertir())

    @PutMapping("/libros")
    fun actualizarLibro(@RequestBody nuevoLibro: LibroDTO): Libro =
        serviceLibros.actualizarLibro(nuevoLibro.convertir())

    @DeleteMapping("/libros/{id}")
    fun borrarLibro(@PathVariable id: Int) = serviceLibros.borrarLibro(id)


}
