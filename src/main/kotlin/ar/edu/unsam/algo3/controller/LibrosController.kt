package ar.edu.unsam.algo3.controller

import BookCreateDTO
import BookDetailDTO
import BookWithBooleansDTO
import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.AgregarLibroRequest
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo3.DTO.AuthorDTO
import ar.edu.unsam.algo3.DTO.AuthorEditDTO
import ar.edu.unsam.algo3.services.ServiceLibros
import org.springframework.web.bind.annotation.*
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class LibrosController(val serviceLibros: ServiceLibros) {

    @GetMapping("/librosTotal")
    fun getAllLibrosLength(): Int = serviceLibros.getAllSize()

    //traer todos los libros
    @GetMapping("/librosSearch")
    fun obtenerSearch(): List<LibroDTO> = serviceLibros.getSearch()

    @GetMapping("/getBooksReact")
    fun getBooksReact(): List<BookWithBooleansDTO> = serviceLibros.getBooksWithBooleans()

    @GetMapping("/getBookReact/{id}")
    fun getBook(@PathVariable id: Int) : BookDetailDTO = serviceLibros.getById(id).toBookDetailDTO()

    @PutMapping("/editBook/{id}")
    fun updateBook(@PathVariable id: Int, @RequestBody request: BookDetailDTO): BookDetailDTO {
        return serviceLibros.updateBook(id, request)
    }

    @PostMapping("/createBook")
    fun createBook(@RequestBody request: BookCreateDTO) {
        return serviceLibros.createBook(request)
    }

    @DeleteMapping("/deleteBook/{id}")
    fun deleteBook(@PathVariable id: Int) = serviceLibros.deleteBook(id)

    //libros por estado
    @GetMapping("/obtenerlibroEstado")
    fun obtenerLibroEstado(@RequestParam estado: Boolean): List<LibroDTO> = serviceLibros.obtenerLibros(estado)

    @PutMapping("/agregarLibroEstado")
    fun postLibroEstado(@RequestBody request: AgregarLibroRequest): List<LibroDTO> {
        return serviceLibros.agregarLibros(request.estado, request.idLibro)
    }

    @DeleteMapping("/eliminarLibroEstado")
    fun borrarLibro(@RequestBody request: AgregarLibroRequest) : List<LibroDTO>{
    return serviceLibros.borrarLibro(request.estado,request.idLibro)
    }
    //libros para leer
    @GetMapping("/add-Books")
    fun agregarALeeer(): List<LibroDTO> = serviceLibros.paraLeer()

    @GetMapping("/librosSearch/filter")
    fun obtenerLibrosFiltrados(@RequestParam filtro: String): List<LibroDTO> = serviceLibros.obtenerLibrosFiltrados(filtro)

}
