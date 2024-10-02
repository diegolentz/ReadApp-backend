package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo3.mock.LIBROS
import excepciones.BusinessException
import org.springframework.stereotype.Service


@Service
object ServiceLibros {
    val repoLibro: Repositorio<Libro> = Repositorio()

init {
    LIBROS.forEach() {
        repoLibro.create(it)
    }
}

    fun get(): List<Libro> = repoLibro.getAll().toList()

    fun nuevoLibro(libro: Libro): Libro {
        libroValido(libro)
        repoLibro.create(libro)
        return getById(libro.id)
    }

    fun getById(libroId: Int): Libro = repoLibro.getByID(libroId)

    fun actualizarLibro(libro: Libro): Libro {
        libroValido(libro)
        repoLibro.update(libro)
        return getById(libro.id)
    }

    fun borrarLibro(idLibro: Int): Libro {
        val libro = getById(idLibro)
        repoLibro.delete(libro)
        return libro
    }

    fun libroValido(libro: Libro) {
        if (libro.ediciones <= 0 || libro.ventasSemanales < 0) {
            throw BusinessException("El formato del libro es inválido")
        }
    }
}

