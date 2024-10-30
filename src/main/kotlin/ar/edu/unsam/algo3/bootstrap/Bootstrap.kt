package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.usuario.*
import ar.edu.unsam.algo3.mock.autorPreferidoPica
import ar.edu.unsam.algo3.services.ServiceUser
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
object Bootstrap : CommandLineRunner {
    val serviceUser: ServiceUser = ServiceUser

    override fun run(vararg args: String?) {
        createUsers()
    }

    private fun createUsers() {
        var diego: Usuario = UsuarioBuilder(Usuario())
            .fotoPath("inosuke.jpeg")
            .nombre("Diego").apellido("Lentz").lenguaje(Lenguaje.ESPANIOL)
            .email("diego@hotmail.com").alias("elDiego")
            .username("diego").password("1234")
            .modoLectura(Promedio).tipoPerfil(Precavido)
            .build()

        val valen: Usuario = UsuarioBuilder(Usuario())
            .fotoPath("dwightSchrute.jpeg")
            .nombre("Valen").apellido("").lenguaje(Lenguaje.ESPANIOL)
            .email("valen@hotmail.com").alias("elValen")
            .username("valen").password("valen")
            .modoLectura(Promedio).tipoPerfil(Precavido)
            .build()

        val delfi: Usuario = UsuarioBuilder(Usuario())
            .fotoPath("ippo.jpeg")
            .nombre("Delfi").apellido("").lenguaje(Lenguaje.ESPANIOL)
            .email("delfi@hotmail.com").alias("laDelfi")
            .username("delfi").password("delfi")
            .modoLectura(Promedio).tipoPerfil(Precavido)
            .build()

        val pica: Usuario = UsuarioBuilder(Usuario())
            .fotoPath("kellyKapoor.jpeg")
            .nombre("Pedro").apellido("").lenguaje(Lenguaje.ESPANIOL)
            .email("pica@hotmail.com").alias("elPica")
            .username("pica").password("pica")
            .modoLectura(Promedio).tipoPerfil(Precavido)
            .autoresPreferidos(autorPreferidoPica)
            .build()


        val adrian: Usuario = UsuarioBuilder(Usuario())
            .fotoPath("kevinMalone.jpeg")
            .nombre("Adrian").apellido("Perez").lenguaje(Lenguaje.ESPANIOL)
            .email("adrian@hotmail.com").alias("elAdri")
            .username("adrian").password("adrian")
            .modoLectura(Ansioso).tipoPerfil(Leedor)
            .build()

        val admin: Usuario = UsuarioBuilder(Usuario())
            .fotoPath("kevinMalone.jpeg")
            .nombre("admin").apellido("")
            .email("NULL@NULL").alias("NULL")
            .username("holaholahola").password("admin")
            .build()

        val USERS = listOf(diego, valen, delfi, pica, adrian, admin)

        USERS.forEach { user ->
            serviceUser.userRepository.create(user)
        }

        auxGenerarAmistades(USERS)
    }

    private fun auxGenerarAmistades(usuarios: List<Usuario>) {
        val usuario1 = usuarios.random()
        val usuario2 = usuarios.filter { it != usuario1 }.random()

        usuario1.agregarAmigo(usuario2)
    }
}