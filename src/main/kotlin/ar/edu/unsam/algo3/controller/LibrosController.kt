package ar.edu.unsam.algo3.controller

import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.AgregarLibroRequest
import ar.edu.unsam.algo3.services.ServiceLibros
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("http://localhost:4200"))
@RestController
class LibrosController(val serviceLibros: ServiceLibros) {

    @GetMapping("/librosTotal")
    fun getAllLibrosLength(): Int = serviceLibros.getAllSize()

    //traer todos los libros
    @GetMapping("/librosSearch")
    fun obtenerSearch(): List<LibroDTO> = serviceLibros.getSearch()

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
