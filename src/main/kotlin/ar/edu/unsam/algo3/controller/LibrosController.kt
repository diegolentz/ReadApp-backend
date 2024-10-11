package ar.edu.unsam.algo3.controller

import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.AgregarLibroRequest
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceLibros
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("http://localhost:4200"))
@RestController
class LibrosController(val serviceLibros: ServiceLibros) {

    //traer todos los libros
    @GetMapping("/librosSearch")
    fun obtenerSearch(): List<LibroDTO> = serviceLibros.getSearch()

    //libros por estado
    @GetMapping("/obtenerlibroEstado")
    fun obtenerLibroEstado(@RequestParam idUser: Int, @RequestParam estado: Boolean): List<LibroDTO> = serviceLibros.obtenerLibros(idUser,estado)

    @PutMapping("/agregarLibroEstado")
    fun postLibroEstado(@RequestBody request: AgregarLibroRequest): List<LibroDTO> {
        return serviceLibros.agregarLibros(request.idUser, request.estado, request.idLibro)
    }

    @DeleteMapping("/libroLeido")
    fun borrarLibroLeido(@RequestParam idLibro: Int, @RequestParam idUser: Int) = serviceLibros.borrarLibroLeido(idLibro, idUser)

    @DeleteMapping("/libroALeer")
    fun borrarLibroALeer(@RequestParam idLibro: Int, @RequestParam idUser: Int) = serviceLibros.borrarLibroLeer(idLibro, idUser)

    //libros para leer
    @GetMapping("/add-Books")
    fun agregarALeeer(@RequestParam idUser: Int): List<LibroDTO> = serviceLibros.paraLeer(idUser)

    @GetMapping("/librosSearch/filter")
    fun obtenerLibrosFiltrados(@RequestParam filtro: String): List<LibroDTO> = serviceLibros.obtenerLibrosFiltrados(filtro)

}
