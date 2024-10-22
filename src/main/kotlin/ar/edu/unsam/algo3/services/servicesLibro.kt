package ar.edu.unsam.algo3.services

import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.mock.LIBROS
import excepciones.BusinessException
import org.springframework.stereotype.Service

@Service
object ServiceLibros {
    val repoLibro: Repositorio<Libro> = Repositorio()
    var libros: MutableList<Libro> = mutableListOf()


    init {
        LIBROS.forEach {
            repoLibro.create(it)
        }
    }
    fun get() : List<Libro> = repoLibro.getAll().toList()

    fun getById(libroId: Int): Libro = repoLibro.getByID(libroId)


    fun getSearch(): List<LibroDTO> {
        libros = repoLibro.getAll().toMutableList()
        return libros.map { it : Libro -> it.toDTO() }
    }

    fun obtenerLibros(idUser: Int, estado: Boolean): List<LibroDTO> {
        val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())
        val libros: List<Libro> = usuario.mostrarLibros(estado)
        return libros.map { it.toDTO() }
    }

    fun obtenerLibrosFiltrados(filtro: String): List<LibroDTO> {
        libros = repoLibro.search(filtro).toMutableList()
        return libros.map { it.toDTO() }
    }

    fun agregarLibros(idUser: Int, estado: Boolean, idLibro: List<Int>): List<LibroDTO> {
        val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())
        val libros: List<Libro> = idLibro.map { libroId -> repoLibro.getByID(libroId) }
        val librosAgregados : List<Libro> = usuario.agregarLibros(libros, estado)
        return librosAgregados.map { it.toDTO() }
    }
    fun borrarLibro(idUser: Int, estado: Boolean, idLibro: List<Int>) : List<LibroDTO>{
        val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())
        val libros: List<Libro> = idLibro.map { libroId -> repoLibro.getByID(libroId) }
        val librosEliminados : List<Libro> = usuario.eliminarLibros(libros, estado)
        return librosEliminados.map { it.toDTO() }
    }
    fun paraLeer(idUser: Int): List<LibroDTO> {
        val usuario = ServiceUser.getByIdRaw(idUser.toString())
        val libros = this.get()
       val agregarParaLeer : List<Libro> = usuario.agregarALeer(libros)
        return agregarParaLeer.map { it.toDTO() }
    }

}