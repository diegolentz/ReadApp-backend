package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.services.ServiceCentro
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class CentrosController(val serviceCentro: ServiceCentro) {

    @GetMapping("/centrosTotal")
    fun getAllCentrosLength(): Int = serviceCentro.getAllSize()
}