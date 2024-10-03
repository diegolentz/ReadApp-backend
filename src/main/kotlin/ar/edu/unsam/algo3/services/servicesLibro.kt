package ar.edu.unsam.algo3.services

import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
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
        var usuario = ServiceUser.getByIdRaw(idUser)
        usuario.leer(libro)

        return libro
    }

    fun obtenerLeido(idUser: Int) : List<Libro> {
        var usuario = ServiceUser.getByIdRaw(idUser)
        return  usuario.librosLeidos
    }


    fun obtenerALeer(idUser: Int) : Set<Libro> {
        var usuario = ServiceUser.getByIdRaw(idUser)
        return  usuario.librosALeer
    }
    fun agregarALeer(idLibro : Int,idUser : Int): Libro {
        var libro = this.getById(idLibro)
        var usuario = ServiceUser.getByIdRaw(idUser)
        usuario.agregarLibroALeer(libro)

        return libro
    }
    fun getById(libroId: Int): Libro = repoLibro.getByID(libroId)

    fun actualizarLibro(libro: Libro): Libro {
        libroValido(libro)
        repoLibro.update(libro)
        return getById(libro.id)
    }

    fun borrarLibroLeido(idLibro: Int, idUser: Int): Libro {
        val libro = this.getById(idLibro)
        val usuario = ServiceUser.getByIdRaw(idUser)
        usuario.librosLeidos.remove(libro)

        return libro
    }

    fun borrarLibroLeer(idLibro: Int, idUser: Int): Libro {
        val libro = this.getById(idLibro)
        val usuario = ServiceUser.getByIdRaw(idUser)
        usuario.librosALeer.remove(libro)

        return libro
    }

    fun libroValido(libro: Libro) {
        if (libro.ediciones <= 0 || libro.ventasSemanales < 0) {
            throw BusinessException("El formato del libro es inválido")
        }
    }
}