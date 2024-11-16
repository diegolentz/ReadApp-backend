package ar.edu.unsam.algo2.readapp.administrador
import ar.edu.unsam.algo3.services.ServiceLibros
import ar.edu.unsam.algo2.readapp.Mail
import ar.edu.unsam.algo2.readapp.MailSender
import ar.edu.unsam.algo2.readapp.centrosDeLectura.CentroDeLectura
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario

abstract class Proceso() {
    val emailDestinatario:String = "admin@readapp.com.ar"
    //abstract var mailSender: MailSender

    fun ejecutar(mailSender: MailSender): Int {
        this.enviarMail(mailSender)
        return this.realizarAccion()
    }

    abstract fun realizarAccion() : Int

    fun enviarMail(mailSender:MailSender){
        mailSender.enviarMail(
            Mail(from = emailDestinatario,
                to = emailDestinatario,
                asunto = "Se realizó el proceso: ${this::class}",
                cuerpo = "Se realizó el proceso: ${this::class}"
            )
        )
    }
}

class BorrarUsuariosInactivos(
    val repositorioAsociado: Repositorio<Usuario>,
): Proceso() {

    override fun realizarAccion(): Int {
        val usuariosInactivos = this.filtrarUsuariosInactivos()
        usuariosInactivos.forEach{
            repositorioAsociado.delete(it)
        }
        return usuariosInactivos.size
    }

    fun filtrarUsuariosInactivos(): List<Usuario>{
        val usuariosInactivos = repositorioAsociado.objetosEnMemoria.filter{
            this.noGeneroValoracion(it) && this.noGeneroRecomendacion(it) && this.noConsideranAmigo(it)
        }
        return usuariosInactivos
    }

    fun noGeneroRecomendacion(usuario: Usuario):Boolean = usuario.recomendaciones.isEmpty()

    fun noGeneroValoracion(usuario: Usuario):Boolean = usuario.recomendacionesValoradas.isEmpty()

    fun noConsideranAmigo(usuario: Usuario):Boolean = repositorioAsociado.objetosEnMemoria.all{
            usuarioX -> !usuarioX.esAmigoDe(usuario)
    }
}

class ProcesoActualizadorLibros(
    val servicio: ServiceLibros,
    val repositorio: Repositorio<Libro>,
) : Proceso() {
    override fun realizarAccion(): Int {
        ActualizadorLibro.actualizar(servicio, repositorio)
        return servicio.libros.size
    }
}

class ProcesoAgregarAutores(
    val autores: List<Autor>,
    val repositorio: Repositorio<Autor>,
) : Proceso() {
    override fun realizarAccion(): Int {
        autores.forEach { autor: Autor -> repositorio.create(autor) }
        return autores.size
    }
}

class BorrarCentrosInactivos(
    val repositorio: Repositorio<CentroDeLectura>,
) : Proceso() {
    override fun realizarAccion(): Int {
        val centrosExpirados = centrosExpirados()
        centrosExpirados.forEach { repositorio.delete(it) }
        return centrosExpirados.size
    }

    private fun centrosExpirados(): List<CentroDeLectura> {
        return repositorio.objetosEnMemoria.filter { it.estaExpirado() }
    }
}