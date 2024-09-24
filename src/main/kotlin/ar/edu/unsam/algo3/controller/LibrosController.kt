package ar.edu.unsam.algo3.controller

import LibroBuilder
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceAutor
import ar.edu.unsam.algo3.services.ServiceLibros
import org.springframework.web.bind.annotation.*

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

class LibroDTO(
    val id_autor: Int,
    var cantidadPalabras: Int,
    var cantidadPaginas: Int,
    var ediciones: Int,
    var ventasSemanales: Int,
    var traducciones: MutableSet<Lenguaje>,
    var titulo: String,
    var id: Int = -1,
) {
    var autor: Autor = buscarAutor()

    fun buscarAutor(): Autor {
        val autorExistente = ServiceAutor.get().find { it.id == id_autor }
        if (autorExistente != null) {
            return autorExistente
        }
//        Acá debería devolver una excepción avisandoq ue el autor no existe, que hay que crearlo primero o revisar que su ide este bien
        return Autor()
    }

    fun convertir(): Libro =
        LibroBuilder().autor(autor)
            .cantidadPalabras(cantidadPalabras)
            .cantidadPaginas(cantidadPaginas)
            .ediciones(ediciones)
            .ventasSemanales(ventasSemanales)
            .traducciones(traducciones)
            .titulo(titulo)
            .id(id)
            .build()
}
