package ar.edu.unsam.algo3.services

import ar.edu.unsam.algo2.readapp.builders.ParticularBuilder
import ar.edu.unsam.algo2.readapp.centrosDeLectura.CentroDeLectura
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ServiceCentro {
    val repoCentro: Repositorio<CentroDeLectura> = Repositorio()

    init {
        repoCentro.create(
            ParticularBuilder()
                .direccion("La Habana 2252")
                .libro(Libro())
                .fecha(mutableSetOf(LocalDate.now()))
                .duracion(20)
                .build()
        )
    }

    fun get(): List<CentroDeLectura> = repoCentro.getAll().toList()

    fun getAllSize(): Int = repoCentro.getAll().size

}