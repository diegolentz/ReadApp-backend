package ar.edu.unsam.algo3.services

import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import org.springframework.stereotype.Service


@Service
class ServiceLibros {
    val repo: Repositorio<Libro> = Repositorio()

    fun get(): List<Libro> = repo.getAll().toList()

    fun nuevoLibro(libro: Libro): Libro {
        repo.create(libro)
        return libro
    }

    fun getById(libroId: Int): Libro = repo.getByID(libroId)

    fun actualizarLibro(libro: Libro) = repo.update(libro)

}