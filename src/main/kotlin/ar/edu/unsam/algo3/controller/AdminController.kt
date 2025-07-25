package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.DTO.DashboardDTO
import ar.edu.unsam.algo3.services.ServiceAdmin
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController
class AdminController(val serviceAdmin: ServiceAdmin) {
    @DeleteMapping("/borrarUsuariosInactivos")
    fun borrarUsuariosInactivos(): Map<String, Int> = serviceAdmin.borrarUsuariosInactivos()

    @DeleteMapping("/borrarCentrosInactivos")
    fun borrarCentrosInactivos(): Map<String, Int> = serviceAdmin.borrarCentrosInactivos()

    @GetMapping("/dashboard")
    fun dashboardData() : DashboardDTO = serviceAdmin.datosDashboard()

}