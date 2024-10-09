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


    fun getSearch(): List<LibroDTO> {
        libros = repoLibro.getAll().toMutableList()
        return libros.map { it : Libro -> it.toDTO() }
    }

    fun agregarLeido(idLibro : Int,idUser : Int) : Libro {
        var libro = this.getById(idLibro)
        var usuario = ServiceUser.getByIdRaw(idUser.toString())
        usuario.leer(libro)

        return libro
    }

    fun obtenerLibros(idUser: Int, estado: Boolean): List<LibroDTO> {
        val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())
        val libros: List<Libro> = if (estado) {
            usuario.librosLeidos.toList()
        } else {
            usuario.librosALeer.toList()
        }
        return libros.map { it : Libro -> it.toDTO() }
    }

    fun agregarLibros(idUser: Int, estado: Boolean, idLibro: List<Int>): List<LibroDTO> {
        val usuario: Usuario = ServiceUser.getByIdRaw(idUser.toString())

        val libros: List<Libro> = idLibro.map { libroId -> repoLibro.getByID(libroId) }
        libros.forEach { libro ->
            if (estado) {
                usuario.librosLeidos.add(libro)  // Agregar a "Leídos" si estado es true
            } else {
                usuario.librosALeer.add(libro)   // Agregar a "Por leer" si estado es false
            }
        }
        return libros.map { it : Libro -> it.toDTO() }

    }

    fun agregarALeer(idLibro : Int,idUser : Int): Libro {
        var libro = this.getById(idLibro)
        var usuario = ServiceUser.getByIdRaw(idUser.toString())
        usuario.agregarLibroALeer(libro)

        return libro
    }

    fun paraLeer(idUser: Int): List<LibroDTO> {
        val usuario = ServiceUser.getByIdRaw(idUser.toString())
        val aLeer = usuario.librosALeer.toList()
        val leido = usuario.librosLeidos.toList()
        val libros = this.get()

        return libros.filter { it !in leido && it !in aLeer }.map { it.toDTO() }
    }
    fun getById(libroId: Int): Libro = repoLibro.getByID(libroId)

    fun borrarLibroLeido(idLibro: Int, idUser: Int): Libro {
        val libro = this.getById(idLibro)
        val usuario = ServiceUser.getByIdRaw(idUser.toString())
        usuario.librosLeidos.remove(libro)

        return libro
    }

    fun borrarLibroLeer(idLibro: Int, idUser: Int): Libro {
        val libro = this.getById(idLibro)
        val usuario = ServiceUser.getByIdRaw(idUser.toString())
        usuario.librosALeer.remove(libro)

        return libro
    }
}