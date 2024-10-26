package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.services.ServiceAdmin
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
class AdminController(val serviceAdmin: ServiceAdmin) {
    @PostMapping("/borrarUsuariosInactivos")
    fun borrarUsuariosInactivos(): String = serviceAdmin.borrarUsuariosInactivos()

    @PostMapping("/borrarCentrosInactivos")
    fun borrarCentrosInactivos(): String = serviceAdmin.borrarCentrosInactivos()
}