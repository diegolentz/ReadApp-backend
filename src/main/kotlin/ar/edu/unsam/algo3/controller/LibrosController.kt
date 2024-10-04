package ar.edu.unsam.algo3.controller

import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceLibros
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("http://localhost:4200"))
@RestController
class LibrosController(val serviceLibros: ServiceLibros) {

    //traer todos los libros
    @GetMapping("/librosSearch")
    fun obtenerSearch(): List<LibroDTO> = serviceLibros.getSearch()

    //para libros leidos
    @GetMapping("/librosLeidos")
    fun obtenerLeido(@RequestParam idUser: Int) = serviceLibros.obtenerLeido(idUser)

    @PostMapping("/libroLeido")
    fun postLibroLeido(@RequestParam idLibro: Int, @RequestParam idUser: Int) =
        serviceLibros.agregarLeido(idLibro, idUser)

    @DeleteMapping("/libroLeido")
    fun borrarLibroLeido(@RequestParam idLibro: Int, @RequestParam idUser: Int) = serviceLibros.borrarLibroLeido(idLibro, idUser)

    //para libros a leer
    @GetMapping("/librosALeer")
    fun obtenerALeeer(@RequestParam idUser: Int) = serviceLibros.obtenerALeer(idUser)

    @PostMapping("/libroALeer")
    fun postLibroALeer(@RequestParam idLibro: Int, @RequestParam idUser: Int) =
        serviceLibros.agregarALeer(idLibro, idUser)

    @DeleteMapping("/libroALeer")
    fun borrarLibroALeer(@RequestParam idLibro: Int, @RequestParam idUser: Int) = serviceLibros.borrarLibroLeer(idLibro, idUser)

    //libros para leer
    @GetMapping("/add-Books")
    fun agregarALeeer(@RequestParam idUser: Int) = serviceLibros.paraLeer(idUser)

}
