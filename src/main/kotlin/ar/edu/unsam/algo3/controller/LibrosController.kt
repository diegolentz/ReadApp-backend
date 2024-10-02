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
    fun borrarLibro(@RequestParam idLibro: Int, @RequestParam idUser: Int) = serviceLibros.borrarLibro(idLibro, idUser)





//    @GetMapping("/libros/{id}")
//    fun libroPorId(@PathVariable id: Int) = serviceLibros.getById(id)

//    @PutMapping("/libros")
//    fun actualizarLibro(@RequestBody nuevoLibro: LibroDTO): Libro =
//        serviceLibros.actualizarLibro(nuevoLibro.convertir())


}
