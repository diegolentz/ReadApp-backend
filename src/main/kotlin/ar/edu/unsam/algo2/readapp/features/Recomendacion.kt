package ar.edu.unsam.algo2.readapp.features

import LibroDTO
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.AvaliableInstance
import ar.edu.unsam.algo2.readapp.usuario.Usuario

import ar.edu.unsam.algo3.DTO.RecomendacionDTO
import ar.edu.unsam.algo3.DTO.RecomendacionEditarDTO
import ar.edu.unsam.algo3.DTO.ValoracionDTO

//import ar.edu.unsam.algo3.dominio.RecommendationDTO


class Recomendacion(
    var creador: Usuario,
    var librosRecomendados: MutableSet<Libro> = mutableSetOf(),
    var titulo: String = "",
    var contenido: String = "",
    var publica: Boolean = true,
    val valoraciones: MutableSet<Valoracion> = mutableSetOf()
): AvaliableInstance {


    override var id:Int = -1//POR DEFAULT AL FINAL
    //////////////////////////////////////////////////////////////////
    //////////            ESTADOS
    ///////////////////////////////////////////////////////////////////
    fun cambiarAcceso() { publica = !publica }

    //////////////////////////////////////////////////////////////////
    //////////            ACCESIBILIDAD
    ///////////////////////////////////////////////////////////////////
    fun esAccesiblePara(usuario: Usuario): Boolean = this.accesoPublico() || this.accesoPrivado(usuario)
    fun esEditablePor(usuario: Usuario, librosParaAgregar: MutableSet<Libro>): Boolean =
        this.condicionesCreador(usuario, librosParaAgregar) || this.esEditablePorAmigo(usuario, librosParaAgregar)
    fun fueValoradaPor(usuario: Usuario): Boolean = valoraciones.any{it.autor == usuario}
    //////////////////////////////////////////////////////////////////
    //////////         CONDICIONES
    ///////////////////////////////////////////////////////////////////
    private fun condicionesCreador(usuario: Usuario, librosParaAgregar: MutableSet<Libro>): Boolean =
        this.esCreador(usuario) && usuario.leyoLibrosParaAgregar(librosParaAgregar)//Metodo que verifica que el creador haya leido los libros
    private fun esEditablePorAmigo(usuario: Usuario, librosParaAgregar: MutableSet<Libro>) =
        creador.esAmigoDe(usuario) && this.condicionesDeEdicionDelAmigo(usuario, librosParaAgregar)
    private fun condicionesDeEdicionDelAmigo(usuario: Usuario, librosParaAgregar: MutableSet<Libro>): Boolean =
        this.usuarioLeyoRecomendados(usuario) && this.condicionesParaAgregarLibros(usuario, librosParaAgregar)
    private fun condicionesParaAgregarLibros(usuario: Usuario, librosParaAgregar: MutableSet<Libro>): Boolean =
        creador.leyoLibrosParaAgregar(librosParaAgregar) && usuario.leyoLibrosParaAgregar(librosParaAgregar)

    fun puedeDejarValoracion(usuario: Usuario):Boolean = !this.esCreador(usuario) && this.condicionesValoracion(usuario)
    private fun condicionesValoracion(usuario: Usuario): Boolean =
        this.usuarioLeyoRecomendados(usuario) || this.condicionesAutor(usuario)
    fun condicionesAutor(usuario: Usuario):Boolean = this.unicoAutor() && this.sonDeAutorPreferido(usuario)

    fun sonDeAutorPreferido(usuario: Usuario) = librosRecomendados.all { libro -> libro.autor in usuario.autoresPreferidos }


    //////////////////////////////////////////////////////////////////
    //////////            CONDICION
    ///////////////////////////////////////////////////////////////////
    private fun accesoPublico(): Boolean = this.publica
    fun esCreador(usuario: Usuario): Boolean = (usuario === creador)
    private fun accesoPrivado(usuario: Usuario):Boolean = creador.esAmigoDe(usuario)
    fun usuarioLeyoRecomendados(usuario: Usuario): Boolean = librosRecomendados.all{usuario.leido(it)}
    private fun usuarioLeyoAlgunRecomendado(usuario: Usuario):Boolean = librosRecomendados.any { libro -> usuario.librosLeidos.contains(libro) }
    fun unicoAutor(): Boolean =
        librosRecomendados
            .flatMap { listOf(it.autor) } // Convertir el autor en una lista
            .toSet()
            .size == 1

    //////////////////////////////////////////////////////////////////
    //////////            ACCIONES
    ///////////////////////////////////////////////////////////////////
    fun editar(libroParaAgregar: Libro) { librosRecomendados.add(libroParaAgregar) }

    fun actualizar(recomendacionActualizada: RecomendacionEditarDTO){
        this.titulo = recomendacionActualizada.titulo
        this.publica = recomendacionActualizada.publica
        this.contenido = recomendacionActualizada.contenido
        /*this.librosRecomendados = recomendacionActualizada.librosRecomendados.map { it -> it. }*/
    }

    //////////////////////////////////////////////////////////////////
    //////////            CALCULO
    ///////////////////////////////////////////////////////////////////

    fun valoracionTotal() : Int = this.valoraciones.sumOf { it.valor }
    fun valoracionPromedio(): Int {
        return if (this.valoraciones.isEmpty()) {
            0
        } else {
            this.valoracionTotal() / this.valoraciones.size
        }
    }

    fun tiempoNetoLectura(usuario: Usuario): Double {
        return this.tiempodeLectura(usuario) - this.tiempoQueSePuedeAhorrar(usuario)
    }
    fun tiempodeLectura(usuario: Usuario): Double {
        // Sumamos el tiempo de lectura promedio de cada libro en la recomendación
        return  librosRecomendados.sumOf { libro -> usuario.tiempoLecturaFinal(libro = libro)}
    }
    fun tiempoQueSePuedeAhorrar(usuario: Usuario): Double{
        return  if(this.usuarioLeyoAlgunRecomendado(usuario)) this.calcularTiempoAhorrado(usuario)
        else 0.0 //NO se ahorra tiempo
    }
    fun cantidadDeLenguajes(): Int = librosRecomendados.sumOf{ it.cantidadDeLenguajes() }

    fun calcularTiempoAhorrado(usuario: Usuario): Double =
        this.librosLeidosEnRecomendacion(usuario).sumOf { libro -> usuario.tiempoLecturaBase(libro) }
    private fun librosLeidosEnRecomendacion(usuario: Usuario) = librosRecomendados.filter{ libro -> usuario.leido(libro)}

    //override fun cumpleCriterioBusqueda(texto: String) =  creador.apellido === texto ||
    //        librosRecomendados.any { libro -> libro.titulo.contains(texto) ||
    //                valoraciones.any { valoracion -> valoracion.comentario.contains(texto)}}

    override fun cumpleCriterioBusqueda(texto: String): Boolean =
        titulo.lowercase().contains(texto.lowercase())

    fun toDTO(): RecomendacionDTO = RecomendacionDTO(
        creador = this.creador.nombreApellido(creador),
        librosRecomendados = parseLibro(librosRecomendados),
        titulo = titulo,
        contenido = contenido,
        publica = publica,
        valoraciones = parseValoraciones(valoraciones),
        valoracionTotal = this.valoracionPromedio(),
        id = this.id
    )
    fun editarDTO(): RecomendacionEditarDTO = RecomendacionEditarDTO(
        titulo = this.titulo,
        contenido = this.contenido,
        publica = publica,
        librosRecomendados = parseLibro(librosRecomendados),
        id = this.id
    )
    fun parseLibro(libros: MutableSet<Libro>): MutableSet<LibroDTO> = libros.map { it.toDTO() }.toMutableSet()

    fun parseValoraciones(valoraciones: MutableSet<Valoracion>): MutableSet<ValoracionDTO> = valoraciones.map { it.toDTO() }.toMutableSet()
}

