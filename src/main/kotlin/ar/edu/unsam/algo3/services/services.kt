package ar.edu.unsam.algo3.services

import LibroBuilder
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import org.springframework.stereotype.Service


@Service
object ServiceLibros {
    val repo: Repositorio<Libro> = Repositorio()

    fun get(): List<Libro> = repo.getAll().toList()

    fun nuevoLibro(libro: LibroDTO): Libro {
        val libroNuevo = libro.convertir()
        repo.create(libroNuevo)
        return libroNuevo
    }

    fun getById(libroId: Int): Libro = repo.getByID(libroId)

    fun actualizarLibro(libro: Libro) = repo.update(libro)

}

object ServiceAutor {
    val repo: Repositorio<Autor> = Repositorio()

    fun get(): List<Autor> = repo.getAll().toList()

}

class LibroDTO(
    val id_autor: Int = 0,
    var cantidadPalabras: Int = 1,
    var cantidadPaginas: Int = 1,
    var ediciones: Int = 1,
    var ventasSemanales: Int = 0,
    var traducciones: MutableSet<Lenguaje> = mutableSetOf(),
    var titulo: String = ""
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
            .build()
}
