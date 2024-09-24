package ar.edu.unsam.algo3.services

import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import excepciones.BusinessException
import org.springframework.stereotype.Service


@Service
object ServiceLibros {
    val repo: Repositorio<Libro> = Repositorio()

    fun get(): List<Libro> = repo.getAll().toList()

    fun nuevoLibro(libro: Libro): Libro {
        libroValido(libro)
        repo.create(libro)
        return getById(libro.id)
    }

    fun getById(libroId: Int): Libro = repo.getByID(libroId)

    fun actualizarLibro(libro: Libro) : Libro {
        libroValido(libro)
        repo.update(libro)
        return getById(libro.id)
    }

    fun borrarLibro(idLibro: Int) : Libro{
        val libro = getById(idLibro)
        repo.delete(libro)
        return libro
    }

    fun libroValido(libro: Libro){
        if (libro.ediciones <= 0 || libro.ventasSemanales < 0) {
            throw BusinessException("El formato del libro es inválido")
        }
    }

}

object ServiceAutor {
    val repo: Repositorio<Autor> = Repositorio()

    fun get(): List<Autor> = repo.getAll().toList()

}

