package ar.edu.unsam.algo3.controller


import ar.edu.unsam.algo2.readapp.libro.Autor
import org.springframework.web.bind.annotation.*
import ar.edu.unsam.algo3.services.ServiceAutor
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController

class AutorController(val serviceAutor: ServiceAutor) {
    @GetMapping("/autores")
    fun obtenerAutores(): List<Autor> = serviceAutor.get()

    @GetMapping("/autores/{id}")
    fun autorPorId(@PathVariable id: Int) = serviceAutor.getById(id)

    @DeleteMapping("/autores/{id}")
    fun borrarAutor(@PathVariable id: Int) = serviceAutor.borrarAutor(id)
}