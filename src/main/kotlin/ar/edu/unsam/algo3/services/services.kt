package ar.edu.unsam.algo3.services

import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import org.springframework.stereotype.Service


@Service
object ServiceLibros {
    val repo: Repositorio<Libro> = Repositorio()

    fun get(): List<Libro> = repo.getAll().toList()

    fun nuevoLibro(libro: Libro): Libro {
        repo.create(libro)
        return getById(libro.id)
    }

    fun getById(libroId: Int): Libro = repo.getByID(libroId)

    fun actualizarLibro(libro: Libro) : Libro {
        repo.update(libro)
        return getById(libro.id)
    }

}

object ServiceAutor {
    val repo: Repositorio<Autor> = Repositorio()

    fun get(): List<Autor> = repo.getAll().toList()

}

