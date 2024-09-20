package ar.edu.unsam.algo3.services

import ActualizadorLibro
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.AvaliableInstance
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import org.springframework.stereotype.Service


@Service
class ServiceLibros() {
    val actualizador: ActualizadorLibro = ActualizadorLibro
    val repo: Repositorio<Libro> = Repositorio<Libro>()

    fun get(): List<Libro> = repo.objetosEnMemoria.toList()

    fun nuevoLibro(libro: Libro) : Libro {
        repo.create(libro)
        return libro
    }

}