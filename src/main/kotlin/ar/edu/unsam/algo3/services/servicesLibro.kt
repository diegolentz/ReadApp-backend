package ar.edu.unsam.algo3.services

import BookCreateDTO
import BookDetailDTO
import BookWithBooleansDTO
import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.DTO.AuthorCreateDTO
import ar.edu.unsam.algo3.services.ServiceAutor.repoAutor
import excepciones.BusinessException
import org.springframework.stereotype.Service

@Service
object ServiceLibros {
    val repoLibro: Repositorio<Libro> = Repositorio()
    var libros: MutableList<Libro> = mutableListOf()


//    init {
//        LIBROS.forEach {
//            repoLibro.create(it)
//        }
//    }
    fun get() : List<Libro> = repoLibro.getAll().toList()

    fun getById(libroId: Int): Libro = repoLibro.getByID(libroId)

    fun getSearch(): List<LibroDTO> {
        libros = repoLibro.getAll().toMutableList()
        if (libros.isEmpty()) {
            throw BusinessException("No se encontraron libros")
        }
        return libros.map { it: Libro -> it.toDTO() }
    }

    fun getBooksWithBooleans(): List<BookWithBooleansDTO> {
        libros = repoLibro.getAll().toMutableList()
        if (libros.isEmpty()) {
            throw BusinessException("No se encontraron libros")
        }
        return libros.map { it: Libro -> it.toBookWithBooleansDTO() }
    }

    fun updateBook(id: Int, bookDTO: BookDetailDTO): BookDetailDTO {
        val book = this.getById(id)
        val updatedBook = book.update(bookDTO)
        return updatedBook.toBookDetailDTO()
    }


    //fun getBookDetail(bookId: Int): BookDetailDTO {
    //    val book = getById(bookId)
    //    return book.toBookDetailDTO()}

    fun deleteBook(id: Int) {
        var book = this.getById(id)
        repoLibro.delete(book)
    }

    fun createBook(book: BookCreateDTO) {
        val author = ServiceAutor.getById(book.author)
        try {
            repoLibro.create(
                Libro(
                    titulo = book.title,
                    autor = author,
                    cantidadPaginas = book.numberOfPages,
                    cantidadPalabras = book.numberOfWords,
                    traducciones = book.translations,
                    ediciones = book.numberOfEditions,
                    imagen = "newBook.png",
                    ventasSemanales = book.weeklySales,
                    esComplejo = book.complex,
                ))
        } catch (e: Exception) {
            throw BusinessException("Book cant be created")
        }
    }

    fun obtenerLibros(estado: Boolean): List<LibroDTO> {
        val idUser = ServiceUser.loggedUserId
        val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())
        val libros: List<Libro> = usuario.mostrarLibros(estado)
        if (libros.size == 0) {
            throw BusinessException("No se encontraron libros.")
        }else{
            return libros.map { it.toDTO() }
        }
    }

    fun obtenerLibrosFiltrados(filtro: String): List<LibroDTO> {

        val libros = repoLibro.search(filtro).toMutableList()
        if (libros.isEmpty()) {
            throw BusinessException("No se encontraron busquedas cohinicidentes")

        }else{
            return libros.map { it.toDTO() }
        }

    }


    fun agregarLibros(estado: Boolean, idLibro: List<Int>): List<LibroDTO> {
        try {
            val idUser = ServiceUser.loggedUserId
            val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())
            val libros: List<Libro> = idLibro.map { libroId -> repoLibro.getByID(libroId) }
            val librosAgregados: List<Libro> = usuario.agregarLibros(libros, estado)
            return librosAgregados.map { it.toDTO() }
        }catch (e: Exception){
            throw BusinessException("No se encontraron libros")
        }
    }

    fun borrarLibro(estado: Boolean, idLibro: List<Int>): List<LibroDTO> {
        try {
            val idUser = ServiceUser.loggedUserId
            val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())
            val libros: List<Libro> = idLibro.map { libroId -> repoLibro.getByID(libroId) }
            val librosEliminados: List<Libro> = usuario.eliminarLibros(libros, estado)
            return librosEliminados.map { it.toDTO() }
        } catch (e: Exception) {
            throw BusinessException("No se encontraron libros")
        }
    }

    fun paraLeer(): List<LibroDTO> {
        val idUser = ServiceUser.loggedUserId
        val usuario = ServiceUser.getByIdRaw(idUser.toString())
        val libros = this.get()
        val agregarParaLeer: List<Libro> = usuario.agregarALeer(libros)
        if (agregarParaLeer.isEmpty()) {
            throw BusinessException("No se encontraron libros")
        }
        return agregarParaLeer.map { it.toDTO() }
    }

    fun getAllSize(): Int = repoLibro.getAll().size

    fun escribioLibro(autor : Autor) : Boolean  {
        val libros = repoLibro.getAll().toList()
        return libros.any { it.autor == autor }
    }
}




