package ar.edu.unsam.algo3.controller


import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo3.DTO.AuthorCreateDTO
import ar.edu.unsam.algo3.DTO.AuthorDTO
import ar.edu.unsam.algo3.DTO.AuthorEditDTO
import ar.edu.unsam.algo3.DTO.LenguajeDTO
import org.springframework.web.bind.annotation.*
import ar.edu.unsam.algo3.services.ServiceAutor

@CrossOrigin(origins = arrayOf("http://localhost:4200","http://localhost:5173" ))

@RestController

class AutorController(val serviceAutor: ServiceAutor) {
    @GetMapping("/allAuthors")
    fun obtenerAutores(): List<AuthorDTO> = serviceAutor.getAll()

    @GetMapping("/getAutor/{id}")
    fun autorPorId(@PathVariable id: Int) : AuthorDTO = serviceAutor.editAutor(id)

    @GetMapping("/lenguajes")
    fun obtenerLenguajes(): List<LenguajeDTO> = listOf(serviceAutor.obtenerLenguajes())

    @GetMapping("/findAuthor")
    fun filter(@RequestParam filter : String) : List<AuthorDTO> = serviceAutor.getFilter(filter)

    @DeleteMapping("/deleteAutor/{id}")
    fun borrarAutor(@PathVariable id: Int) : Autor = serviceAutor.borrarAutor(id)

    @PutMapping("/editAuthor")
    fun actualizarAuthor(@RequestBody request : AuthorEditDTO) : Autor{
        return serviceAutor.actualizarAuthor(request)
    }

    @PostMapping("/createAuthor")
    fun crearAutor(@RequestBody request : AuthorCreateDTO) : Boolean{
        return serviceAutor.crearAutor(request)
    }

    @GetMapping("/allAuthorsForBooks")
    fun getAuthorsForBooks(): List<AuthorEditDTO> = serviceAutor.getAllForBooks()
}
