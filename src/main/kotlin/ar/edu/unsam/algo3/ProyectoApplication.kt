package ar.edu.unsam.algo3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["ar.edu.unsam"])
class ProyectoApplication

fun main(args: Array<String>) {
    runApplication<ProyectoApplication>(*args)
}
