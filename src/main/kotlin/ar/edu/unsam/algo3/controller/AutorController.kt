package ar.edu.unsam.algo3.controller


import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo3.DTO.AuthorEditDTO
import ar.edu.unsam.algo3.DTO.AutorDTO
import org.springframework.web.bind.annotation.*
import ar.edu.unsam.algo3.services.ServiceAutor

@CrossOrigin(origins = arrayOf("http://localhost:4200","http://localhost:5173" ))

@RestController

class AutorController(val serviceAutor: ServiceAutor) {
    @GetMapping("/reactAutor")
    fun obtenerAutores(): List<AutorDTO> = serviceAutor.getAll()

    @GetMapping("/reactAutor/{id}")
    fun autorPorId(@PathVariable id: Int) : AuthorEditDTO = serviceAutor.editAutor(id)

    @DeleteMapping("/reactAutor/{id}")
    fun borrarAutor(@PathVariable id: Int) : Autor = serviceAutor.borrarAutor(id)

    @PutMapping("/editAuthor")
    fun actualizarAuthor(@RequestBody request : AutorDTO) : Autor{
        return serviceAutor.actualizarAuthor(request)
    }

}
