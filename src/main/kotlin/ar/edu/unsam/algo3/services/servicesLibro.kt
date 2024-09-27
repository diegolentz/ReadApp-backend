package ar.edu.unsam.algo3.services

import LibroBuilder
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio

import excepciones.BusinessException
import org.springframework.stereotype.Service


@Service
object ServiceLibros {
    val repoLibro: Repositorio<Libro> = Repositorio()

    // creo libros standar
    init {
        // necesito los autores
        val autores = ServiceAutor.get()

        // Crear libros
        val libro1 = LibroBuilder()
            .autor(autores[0])
            .cantidadPalabras(80000)
            .cantidadPaginas(400)
            .ediciones(5)
            .ventasSemanales(300)
            .traducciones(mutableSetOf(Lenguaje.ESPANIOL    ))
            .titulo("El mar en calma")
            .imagen("https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg")
            .build()

        val libro2 = LibroBuilder()
            .autor(autores[1])
            .cantidadPalabras(75000)
            .cantidadPaginas(350)
            .ediciones(3)
            .ventasSemanales(500)
            .traducciones(mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES))
            .titulo("Sombras en la noche")
            .imagen("https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg")
            .build()
        val libro3 = LibroBuilder()
            .autor(autores[2])
            .cantidadPalabras(75000)
            .cantidadPaginas(350)
            .ediciones(3)
            .ventasSemanales(500)
            .traducciones(mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES))
            .titulo("La ciudad perdida")
            .imagen("https://image.tmdb.org/t/p/original/grEVYkBAVIzQ4JmZ7ydceN9DFQR.jpg")
            .build()
        val libro4 = LibroBuilder()
            .autor(autores[3])
            .cantidadPalabras(75000)
            .cantidadPaginas(350)
            .ediciones(3)
            .ventasSemanales(500)
            .traducciones(mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES))
            .titulo("El misterio del lago")
            .imagen("https://st.booknet.com/uploads/covers/220/1548248964_73.jpg")
            .build()
        val libro5 = LibroBuilder()
            .autor(autores[4])
            .cantidadPalabras(75000)
            .cantidadPaginas(350)
            .ediciones(3)
            .ventasSemanales(500)
            .traducciones(mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES))
            .titulo("Un viaje inesperado")
            .imagen("https://http2.mlstatic.com/D_NQ_NP_998326-MLC54028482109_022023-O.webp")
            .build()
        val libro6 = LibroBuilder()
            .autor(autores[0])
            .cantidadPalabras(75000)
            .cantidadPaginas(350)
            .ediciones(3)
            .ventasSemanales(500)
            .traducciones(mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES))
            .titulo("El último refugio")
            .imagen("https://proassetspdlcom.cdnstatics2.com/usuaris/libros/thumbs/54fbdd8d-9558-4328-bd98-983b52175273/m_175_310/329653_el-ultimo-refugio_9786070768958_3d_202104151753.webp")
            .build()
        val libro7 = LibroBuilder()
            .autor(autores[1])
            .cantidadPalabras(75000)
            .cantidadPaginas(350)
            .ediciones(3)
            .ventasSemanales(500)
            .traducciones(mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES))
            .titulo("La última frontera")
            .imagen("https://tse2.mm.bing.net/th?id=OIP.hrEnCP84kpA99IFJkR7BywAAAA&pid=Api&P=0&h=180")
            .build()
        val libro8 = LibroBuilder()
            .autor(autores[2])
            .cantidadPalabras(75000)
            .cantidadPaginas(350)
            .ediciones(3)
            .ventasSemanales(500)
            .traducciones(mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES))
            .titulo("Caminos de fuego")
            .imagen("https://media.s-bol.com/qPn8Q9DA0q0/550x827.jpg")
            .build()

        // Agregar libros al repositorio
        repoLibro.create(libro1)
        repoLibro.create(libro2)
        repoLibro.create(libro3)
        repoLibro.create(libro4)
        repoLibro.create(libro5)
        repoLibro.create(libro6)
        repoLibro.create(libro7)
        repoLibro.create(libro8)
    }

    fun get(): List<Libro> = repoLibro.getAll().toList()

    fun nuevoLibro(libro: Libro): Libro {
        libroValido(libro)
        repoLibro.create(libro)
        return getById(libro.id)
    }

    fun getById(libroId: Int): Libro = repoLibro.getByID(libroId)

    fun actualizarLibro(libro: Libro): Libro {
        libroValido(libro)
        repoLibro.update(libro)
        return getById(libro.id)
    }

    fun borrarLibro(idLibro: Int): Libro {
        val libro = getById(idLibro)
        repoLibro.delete(libro)
        return libro
    }

    fun libroValido(libro: Libro) {
        if (libro.ediciones <= 0 || libro.ventasSemanales < 0) {
            throw BusinessException("El formato del libro es inválido")
        }
    }
}

