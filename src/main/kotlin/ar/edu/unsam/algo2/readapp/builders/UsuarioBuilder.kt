package ar.edu.unsam.algo2.readapp.builders

import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.usuario.PerfilDeUsuario
import ar.edu.unsam.algo2.readapp.usuario.TipoDeLector
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import java.time.LocalDate

class UsuarioBuilder(val nuevoUsuario: Usuario = Usuario()) {
    
    fun nombre(nombre: String): UsuarioBuilder = apply {
        nuevoUsuario.nombre = nombre
    }

    fun apellido(apellido: String): UsuarioBuilder = apply {
        nuevoUsuario.apellido = apellido
    }

    fun alias(alias: String): UsuarioBuilder = apply {
        nuevoUsuario.alias = alias
    }

    fun fechaNacimiento(date: LocalDate): UsuarioBuilder = apply {
        nuevoUsuario.fechaNacimiento = date
    }

    fun velocidadLectura(velocidad: Int): UsuarioBuilder = apply {
        nuevoUsuario.palabrasPorMinutos = velocidad
    }

    fun email(email: String): UsuarioBuilder = apply {
        nuevoUsuario.direccionMail = email
        nuevoUsuario.email = email
    }

    fun lenguaje(lenguaje: Lenguaje): UsuarioBuilder = apply {
        nuevoUsuario.lenguaje = lenguaje
    }

    fun username(username: String): UsuarioBuilder = apply {
        nuevoUsuario.username = username
    }
    fun password(password: String): UsuarioBuilder = apply {
        nuevoUsuario.password = password
    }
    fun modoLectura(opcion: TipoDeLector): UsuarioBuilder = apply {
        nuevoUsuario.tipoDeLector = opcion
    }
    fun tipoPerfil(opcion: PerfilDeUsuario): UsuarioBuilder = apply {
        nuevoUsuario.perfil = opcion
    }

    fun fotoPath(path: String): UsuarioBuilder = apply {
        nuevoUsuario.fotoPath = path
    }

    fun autoresPreferidos(autor:Autor):UsuarioBuilder = apply {
        nuevoUsuario.autoresPreferidos.add(autor)
    }
    fun build(): Usuario = nuevoUsuario
}