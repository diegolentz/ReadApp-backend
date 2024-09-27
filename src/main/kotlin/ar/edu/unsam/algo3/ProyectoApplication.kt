package ar.edu.unsam.algo3

import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["ar.edu.unsam"])
class ProyectoApplication

fun main(args: Array<String>) {
    runApplication<ProyectoApplication>(*args)



}
