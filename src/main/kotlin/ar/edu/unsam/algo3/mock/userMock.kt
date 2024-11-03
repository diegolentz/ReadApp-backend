package ar.edu.unsam.algo3.mock

import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.usuario.Leedor
import ar.edu.unsam.algo2.readapp.usuario.*
import ar.edu.unsam.algo2.readapp.usuario.Promedio
import ar.edu.unsam.algo2.readapp.usuario.Usuario

val PHOTOS_PATH = listOf<String>("random_1.jpeg", "random_2.jpeg")

var diego:Usuario = UsuarioBuilder(Usuario())
    .fotoPath("inosuke.jpeg")
    .nombre("Diego").apellido("Lentz").lenguaje(Lenguaje.ESPANIOL)
    .email("diego@hotmail.com").alias("elDiego")
    .username("diego").password("1234")
    .modoLectura(Promedio).tipoPerfil(Precavido)
    .build()

val valen:Usuario = UsuarioBuilder(Usuario())
    .fotoPath("dwightSchrute.jpeg")
    .nombre("Valen").apellido("").lenguaje(Lenguaje.ESPANIOL)
    .email("valen@hotmail.com").alias("elValen")
    .username("valen").password("valen")
    .modoLectura(Promedio).tipoPerfil(Precavido)
    .build()

val delfi:Usuario = UsuarioBuilder(Usuario())
    .fotoPath("ippo.jpeg")
    .nombre("Delfi").apellido("").lenguaje(Lenguaje.ESPANIOL)
    .email("delfi@hotmail.com").alias("laDelfi")
    .username("delfi").password("delfi")
    .modoLectura(Promedio).tipoPerfil(Precavido)
    .build()

val pica:Usuario = UsuarioBuilder(Usuario())
    .fotoPath("kellyKapoor.jpeg")
    .nombre("Pedro").apellido("").lenguaje(Lenguaje.ESPANIOL)
    .email("pica@hotmail.com").alias("elPica")
    .username("pica").password("pica")
    .modoLectura(Promedio).tipoPerfil(Precavido)
    .autoresPreferidos(autorPreferidoPica)
    .build()


val adrian:Usuario = UsuarioBuilder(Usuario())
    .fotoPath("kevinMalone.jpeg")
    .nombre("Adrian").apellido("Perez").lenguaje(Lenguaje.ESPANIOL)
    .email("adrian@hotmail.com").alias("elAdri")
    .username("adrian").password("adrian")
    .modoLectura(Ansioso).tipoPerfil(Leedor)
    .build()

val admin:Usuario = UsuarioBuilder(Usuario())
    .fotoPath("kevinMalone.jpeg")
    .nombre("admin").apellido("")
    .email("NULL@NULL").alias("NULL")
    .username("holaholahola").password("admin")
    .build()

val USERS = listOf(diego, valen, delfi, pica, adrian, admin)

fun auxGenerarAmistades(){
    diego.agregarAmigo(valen)
    diego.agregarAmigo(adrian)

    valen.agregarAmigo(diego)
    valen.agregarAmigo(delfi)

    delfi.agregarAmigo(valen)
    delfi.agregarAmigo(pica)

    pica.agregarAmigo(delfi)
    pica.agregarAmigo(adrian)

    adrian.agregarAmigo(pica)
    adrian.agregarAmigo(diego)
}

fun auxGenerarRecomendaciones(){
    diego.leer(LIBROS[0])
    diego.leer(LIBROS[1])
    diego.leer(LIBROS[2])
    diego.agregarLibroALeer(LIBROS[3])
    diego.agregarLibroALeer(LIBROS[4])
    diego.agregarLibroALeer(LIBROS[5])
    diego.agregarLibroALeer(LIBROS[6])
    diego.agregarLibroALeer(LIBROS[7])
    diego.agregarLibroALeer(LIBROS[8])
    diego.crearRecomendacion(
        titulo = "Historias Cortas de Ciencia Ficción",
        contenido = "En un futuro donde las máquinas dominan el espacio exterior, un pequeño grupo de humanos lucha por sobrevivir. Esta colección reúne relatos que exploran la inteligencia artificial, los viajes intergalácticos y las fronteras de la ciencia moderna, presentando una visión fascinante del futuro.",
        publico = true,
        librosParaRecomendar = mutableSetOf(LIBROS[0], LIBROS[1])
    )
    diego.crearRecomendacion(
        titulo = "La Filosofía del Zen",
        contenido = "Este libro profundiza en las enseñanzas milenarias del Zen, una rama del budismo que enfatiza la meditación y la comprensión intuitiva del ser. A través de metáforas y historias breves, el autor invita al lector a reflexionar sobre la naturaleza del ser, la vida y el equilibrio interno.",
        publico = false,
        librosParaRecomendar = mutableSetOf(LIBROS[2])
    )

    valen.leer(LIBROS[2])
    valen.leer(LIBROS[3])
    valen.leer(LIBROS[4])
    valen.crearRecomendacion(
        titulo = "Viajes por Mundos Desconocidos",
        contenido = "Desde los océanos más profundos hasta los confines del espacio exterior, este libro lleva al lector a una travesía inolvidable. Explorando los límites de la realidad y la imaginación, el autor presenta una serie de aventuras que desafían las leyes de la física y de la mente humana.",
        publico = true,
        librosParaRecomendar = mutableSetOf(LIBROS[2], LIBROS[3])
    )
    valen.crearRecomendacion(
        titulo = "El Arte de la Guerra Moderna",
        contenido = "Un análisis profundo de las estrategias militares contemporáneas, desde las guerras cibernéticas hasta las tácticas de guerrilla urbana. Este libro examina cómo las tecnologías avanzadas y los conflictos geopolíticos han transformado el campo de batalla moderno, ofreciendo una perspectiva única sobre el futuro de la guerra.",
        publico = false,
        librosParaRecomendar = mutableSetOf(LIBROS[3], LIBROS[4])
    )

    delfi.leer(LIBROS[5])
    delfi.leer(LIBROS[6])
    delfi.leer(LIBROS[7])
    delfi.agregarAutorFavorito(AUTOR[0])
    delfi.crearRecomendacion(
        titulo = "El Universo en Expansión",
        contenido = "Este fascinante relato explora los últimos descubrimientos en cosmología, desde la teoría del Big Bang hasta la energía oscura. A través de una narrativa cautivadora, el autor nos lleva a comprender la inmensidad del universo y las incógnitas que aún desafían a los científicos de todo el mundo.",
        publico = true,
        librosParaRecomendar = mutableSetOf(LIBROS[5])
    )
    delfi.crearRecomendacion(
        titulo = "Misterios de la Historia Antigua",
        contenido = "Un viaje a través de los enigmas más grandes de las civilizaciones antiguas: desde las pirámides de Egipto hasta los secretos de la Atlántida. Este libro examina los misterios no resueltos y teorías fascinantes sobre culturas que dejaron un legado duradero, pero aún ocultan verdades por descubrir.",
        publico = true,
        librosParaRecomendar = mutableSetOf(LIBROS[6], LIBROS[7])
    )


    pica.leer(LIBROS[7])
    pica.leer(LIBROS[6])
    pica.agregarAutorFavorito(AUTOR[0])
    pica.crearRecomendacion(
        titulo = "Misterios de la Historia Antigua 2",
        contenido = "Continuacion del viaje, sarasa",
        publico = false,
        librosParaRecomendar = mutableSetOf(LIBROS[6], LIBROS[7])
    )


    LIBROS.forEach{
        adrian.leer(it)
    }

    adrian.crearRecomendacion(
        titulo = "El Legado de las Civilizaciones Perdidas",
        contenido = "Un viaje fascinante a través de las antiguas civilizaciones que desaparecieron sin dejar rastro.",
        publico = false,
        librosParaRecomendar = mutableSetOf(LIBROS[9], LIBROS[10])
    )
    adrian.crearRecomendacion(
        titulo = "La Ciencia de los Sueños Lúcidos",
        contenido = "Cómo los sueños pueden ser controlados y los secretos detrás de esta experiencia surrealista.",
        publico = true,
        librosParaRecomendar = mutableSetOf(LIBROS[1], LIBROS[9])
    )
    adrian.crearRecomendacion(
        titulo = "El Arte de la Meditación en la Vida Moderna",
        contenido = "Guía práctica para integrar la meditación en la vida diaria y reducir el estrés.",
        publico = true,
        librosParaRecomendar = mutableSetOf(LIBROS[3], LIBROS[4],LIBROS[10])
    )
    adrian.crearRecomendacion(
        titulo = "Misterios del Universo: Desde los Agujeros Negros hasta la Materia Oscura",
        contenido = "Un análisis profundo sobre los fenómenos más enigmáticos del universo.",
        publico = false,
        librosParaRecomendar = mutableSetOf(LIBROS[1])
    )
    adrian.leer(LIBROS[7])
    adrian.crearRecomendacion(
        titulo = "Libro de autor unico",
        contenido = "Continuacion del viaje, sarasa",
        publico = true,
        librosParaRecomendar = mutableSetOf(LIBROS[7])
    )
}

fun auxGenerarRecomendacionesAValorar(){
    val recomendaciones = mutableListOf<Recomendacion>()
    USERS.forEach { usuario ->
        usuario.recomendaciones.forEach{
            recomendaciones.add(it)
        }
    }

    adrian.agregarRecomendacionAValorar(recomendaciones[0])
    adrian.agregarRecomendacionAValorar(recomendaciones[5])
}