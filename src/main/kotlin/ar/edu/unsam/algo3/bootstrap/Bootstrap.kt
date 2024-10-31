package ar.edu.unsam.algo3.bootstrap

import LibroBuilder
import ar.edu.unsam.algo2.readapp.builders.UsuarioBuilder
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.usuario.*
import ar.edu.unsam.algo3.mock.autorPreferidoPica
import ar.edu.unsam.algo3.services.ServiceAutor
import ar.edu.unsam.algo3.services.ServiceLibros
import ar.edu.unsam.algo3.services.ServiceRecommendation
import ar.edu.unsam.algo3.services.ServiceUser
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
object Bootstrap : CommandLineRunner {
    val serviceUser: ServiceUser = ServiceUser
    val serviceBooks: ServiceLibros = ServiceLibros
    val serviceAutor: ServiceAutor = ServiceAutor
    val serviceRecomendacion: ServiceRecommendation = ServiceRecommendation

    override fun run(vararg args: String?) {
        val users = createUsers()
        val books = createBooks()
        createRecommendations(users, books)
    }

    private fun createUsers(): List<Usuario> {
        val diego: Usuario = UsuarioBuilder(Usuario())
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
            auxGenerarAmistades(USERS)
        }

        return USERS


    }

    private fun auxGenerarAmistades(usuarios: List<Usuario>) {
        val usuario1 = usuarios.random()
        val usuario2 = usuarios.filter { it != usuario1 }.random()
        usuario1.agregarAmigo(usuario2)
    }

    private fun createBooks(): MutableList<Libro> {
        val autores = createAutors()

        val libro_1: Libro = LibroBuilder()
            .titulo("La sombra sobre Innsmouth").autor(autores.random())
            .cantidadPaginas(850).cantidadPalabras(200 * 850)
            .ventasSemanales(1200).edicionesRandom().traduccionesRandom()
            .imagen("https://www.elejandria.com/covers/La_sombra_sobre_Innsmouth-H._P._Lovecraft-md.png")
            .build()

        val libro_2: Libro = LibroBuilder()
            .titulo("Caminos de fuego").autor(autores.random())
            .cantidadPaginas(600).cantidadPalabras(200 * 600)
            .ventasSemanales(950).edicionesRandom().traduccionesRandom()
            .imagen("https://media.s-bol.com/qPn8Q9DA0q0/550x827.jpg")
            .build()

        val libro_3: Libro = LibroBuilder()
            .titulo("Sombras en la noche").autor(autores.random())
            .cantidadPaginas(400).cantidadPalabras(200 * 400)
            .ventasSemanales(2500).edicionesRandom().traduccionesRandom()
            .imagen("https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg")
            .build()

        val libro_4: Libro = LibroBuilder()
            .titulo("El mar en calma y viaje feliz").autor(autores.random())
            .cantidadPaginas(950).cantidadPalabras(200 * 950)
            .ventasSemanales(3100).edicionesRandom().traduccionesRandom()
            .imagen("https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg")
            .build()

        val libro_5: Libro = LibroBuilder()
            .titulo("Ultima frontera").autor(autores.random())
            .cantidadPaginas(700).cantidadPalabras(200 * 700)
            .ventasSemanales(1800).edicionesRandom().traduccionesRandom()
            .imagen("https://tse2.mm.bing.net/th?id=OIP.hrEnCP84kpA99IFJkR7BywAAAA&pid=Api&P=0&h=180")
            .build()

        val libro_6: Libro = LibroBuilder()
            .titulo("El ultimo refugio").autor(autores.random())
            .cantidadPaginas(530).cantidadPalabras(200 * 530)
            .ventasSemanales(1450).edicionesRandom().traduccionesRandom()
            .imagen("https://proassetspdlcom.cdnstatics2.com/usuaris/libros/thumbs/54fbdd8d-9558-4328-bd98-983b52175273/m_175_310/329653_el-ultimo-refugio_9786070768958_3d_202104151753.webp")
            .build()

        val libro_7: Libro = LibroBuilder()
            .titulo("Un viaje inesperado").autor(autores[0])
            .cantidadPaginas(390).cantidadPalabras(200 * 390)
            .ventasSemanales(2200).edicionesRandom().traduccionesRandom()
            .imagen("https://http2.mlstatic.com/D_NQ_NP_998326-MLC54028482109_022023-O.webp")
            .build()

        val libro_8: Libro = LibroBuilder()
            .titulo("El misterio del lago").autor(autores.random())
            .cantidadPaginas(750).cantidadPalabras(200 * 750)
            .ventasSemanales(2700).edicionesRandom().traduccionesRandom()
            .imagen("https://st.booknet.com/uploads/covers/220/1548248964_73.jpg")
            .build()

        val libro_9: Libro = LibroBuilder()
            .titulo("La ciudad perdida").autor(autores.random())
            .cantidadPaginas(450).cantidadPalabras(200 * 450)
            .ventasSemanales(1950).edicionesRandom().traduccionesRandom()
            .imagen("https://image.tmdb.org/t/p/original/grEVYkBAVIzQ4JmZ7ydceN9DFQR.jpg")
            .build()

        val libro_10: Libro = LibroBuilder()
            .titulo("Sombras de la noche II").autor(autores.random())
            .cantidadPaginas(880).cantidadPalabras(200 * 880)
            .ventasSemanales(3300).edicionesRandom().traduccionesRandom()
            .imagen("https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg")
            .build()

        val libro_11: Libro = LibroBuilder()
            .titulo("El mar en calma y viaje feliz II").autor(autores.random())
            .cantidadPaginas(680).cantidadPalabras(200 * 680)
            .ventasSemanales(1250).edicionesRandom().traduccionesRandom()
            .imagen("https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg")
            .build()

        val libro_12: Libro = LibroBuilder()
            .titulo("El bosque negro").autor(autores.random())
            .cantidadPaginas(520).cantidadPalabras(200 * 520)
            .ventasSemanales(1400).edicionesRandom().traduccionesRandom(6)
            .imagen("https://i.pinimg.com/originals/ab/ec/d5/abecd51b79366c089872d1e88e6c7424.jpg")
            .build()

        val LIBROS = mutableListOf(
            libro_1, libro_2, libro_3, libro_4,
            libro_5, libro_6, libro_7, libro_8,
            libro_9, libro_10, libro_11, libro_12
        )

        LIBROS.forEach({
            serviceBooks.repoLibro.create(it)
        })

        return LIBROS
    }

    private fun createAutors(): List<Autor> {

        val AUTOR = listOf(
            Autor(

                lenguaNativa = Lenguaje.RUSO,
                edad = 50,
                nombre = "Gabriel",
                apellido = "Garcia Marquez",
                seudonimo = "El Gabo"
            ),
            Autor(
                lenguaNativa = Lenguaje.INGLES,
                edad = 42,
                nombre = "J.K.",
                apellido = "Rowling",
                seudonimo = "Robert Galbraith"
            ),
            Autor(
                lenguaNativa = Lenguaje.ESPANIOL,
                edad = 67,
                nombre = "Isabel",
                apellido = "Allende",
                seudonimo = "La Novelista"
            ),
            Autor(
                lenguaNativa = Lenguaje.ALEMAN,
                edad = 55,
                nombre = "Franz",
                apellido = "Kafka",
                seudonimo = "El Existencialista"
            ),
            Autor(
                lenguaNativa = Lenguaje.FRANCES,
                edad = 48,
                nombre = "Marcel",
                apellido = "Proust",
                seudonimo = "El Memorialista"
            )
        )
        AUTOR.forEach(
            { autor -> serviceAutor.repoAutor.create(autor) }
        )

        return AUTOR

    }

    private fun createRecommendations(users: List<Usuario>, books: List<Libro>) {

        val TITULOS = mutableListOf(
            "Historias Cortas de Ciencia Ficción",
            "La Filosofía del Zen",
            "Viajes por Mundos Desconocidos",
            "El Arte de la Guerra Moderna",
            "El Universo en Expansión",
            "Misterios de la Historia Antigua 2",
            "El Legado de las Civilizaciones Perdidas",
            "La Ciencia de los Sueños Lúcidos",
            "El Arte de la Meditación en la Vida Moderna",
            "Libro de autor unico",
        )

        val CONTENIDOS = mutableListOf(
            "En un futuro donde las máquinas dominan el espacio exterior, un pequeño grupo de humanos lucha por sobrevivir. Esta colección reúne relatos que exploran la inteligencia artificial, los viajes intergalácticos y las fronteras de la ciencia moderna, presentando una visión fascinante del futuro.",
            "Este libro profundiza en las enseñanzas milenarias del Zen, una rama del budismo que enfatiza la meditación y la comprensión intuitiva del ser. A través de metáforas y historias breves, el autor invita al lector a reflexionar sobre la naturaleza del ser, la vida y el equilibrio interno.",
            "Desde los océanos más profundos hasta los confines del espacio exterior, este libro lleva al lector a una travesía inolvidable. Explorando los límites de la realidad y la imaginación, el autor presenta una serie de aventuras que desafían las leyes de la física y de la mente humana.",
            "Un análisis profundo de las estrategias militares contemporáneas, desde las guerras cibernéticas hasta las tácticas de guerrilla urbana. Este libro examina cómo las tecnologías avanzadas y los conflictos geopolíticos han transformado el campo de batalla moderno, ofreciendo una perspectiva única sobre el futuro de la guerra.",
            "Este fascinante relato explora los últimos descubrimientos en cosmología, desde la teoría del Big Bang hasta la energía oscura. A través de una narrativa cautivadora, el autor nos lleva a comprender la inmensidad del universo y las incógnitas que aún desafían a los científicos de todo el mundo.",
            "Un viaje a través de los enigmas más grandes de las civilizaciones antiguas: desde las pirámides de Egipto hasta los secretos de la Atlántida. Este libro examina los misterios no resueltos y teorías fascinantes sobre culturas que dejaron un legado duradero, pero aún ocultan verdades por descubrir.",
            "Continuacion del viaje, sarasa"
        )

        val PUBLICOS = mutableListOf(
            true, false
        )

        users.forEach({
            val libro = books.random()
            it.leer(libro)
            it.crearRecomendacion(
                titulo = TITULOS.random(),
                contenido = CONTENIDOS.random(),
                publico = PUBLICOS.random(),
                librosParaRecomendar = mutableSetOf(libro)
            )
        })

        users.forEach({ user ->
            user.recomendaciones.forEach({
                serviceRecomendacion.recommendationRepository.create(it)
            })
        })

    }

}


