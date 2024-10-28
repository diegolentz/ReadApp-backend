package ar.edu.unsam.algo3.services

import ar.edu.unsam.algo2.readapp.administrador.Administrador
import ar.edu.unsam.algo2.readapp.administrador.BorrarCentrosInactivos
import ar.edu.unsam.algo2.readapp.administrador.BorrarUsuariosInactivos
import ar.edu.unsam.algo3.DTO.DashboardDTO
import ar.edu.unsam.algo3.mock.MailSenderStub
import ar.edu.unsam.algo3.services.ServiceUser.userRepository
import ar.edu.unsam.algo3.services.ServiceCentro.repoCentro
import org.springframework.stereotype.Service

@Service
class ServiceAdmin {
    private val admin = Administrador(MailSenderStub())

    fun borrarUsuariosInactivos(): String {
        admin.agregarProceso(BorrarUsuariosInactivos(userRepository))
        return ejecutar()
    }

    fun borrarCentrosInactivos(): String {
        admin.agregarProceso(BorrarCentrosInactivos(repoCentro))
        return ejecutar()
    }

    fun ejecutar() : String{
        admin.ejecutarListaProcesos()
        return "Tarea ejecutada con éxito"
    }

    fun datosDashboard(): DashboardDTO {
        val totalRecomendaciones = ServiceRecommendation.getAllSize()
        val  totalLibros = ServiceLibros.getAllSize()
        val totalUsuarios = ServiceUser.getAllSize()
        val totalCentros = ServiceCentro.getAllSize()
        return DashboardDTO(totalRecomendaciones,totalLibros,totalUsuarios, totalCentros)
    }
}