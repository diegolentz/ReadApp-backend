package ar.edu.unsam.algo3.mock

import LibroBuilder
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceAutor
import kotlin.random.Random


val autores = ServiceAutor.get()

val titulos = listOf(
    "Aventuras en el Fin del Mundo",
    "Secretos de la Montaña Perdida",
    "El Último Reino",
    "Más Allá del Horizonte",
    "Caminos de la Vida",
    "La Ciudad del Silencio",
    "Memorias del Futuro",
    "La Batalla Final",
    "Sombras en el Desierto",
    "El Eco de los Sueños",
    "Cazadores de Estrellas",
    "El Mar en Calma"
)

val imagenes = listOf(
    "https://www.elejandria.com/covers/La_sombra_sobre_Innsmouth-H._P._Lovecraft-md.png",
    "https://media.s-bol.com/qPn8Q9DA0q0/550x827.jpg",
    "https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg",
    "https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg",
    "https://i.pinimg.com/originals/ab/ec/d5/abecd51b79366c089872d1e88e6c7424.jpg",
    "https://tse2.mm.bing.net/th?id=OIP.hrEnCP84kpA99IFJkR7BywAAAA&pid=Api&P=0&h=180",
    "https://proassetspdlcom.cdnstatics2.com/usuaris/libros/thumbs/54fbdd8d-9558-4328-bd98-983b52175273/m_175_310/329653_el-ultimo-refugio_9786070768958_3d_202104151753.webp",
    "https://http2.mlstatic.com/D_NQ_NP_998326-MLC54028482109_022023-O.webp",
    "https://st.booknet.com/uploads/covers/220/1548248964_73.jpg",
    "https://image.tmdb.org/t/p/original/grEVYkBAVIzQ4JmZ7ydceN9DFQR.jpg",
    "https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg",
    "https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg"
)

fun libroRandom(): Libro {
    val builder = LibroBuilder()
        .titulo(titulos.random())
        .autor(autores.random())
        .cantidadPaginas(Random.nextInt(100, 1000))
        .cantidadPalabras(Random.nextInt(1000, 10000))
        .ventasSemanales(Random.nextInt(100, 500))
        .imagen(imagenes.random())

    if (Random.nextBoolean()) builder.bestSeller()
    if (Random.nextBoolean()) builder.complejo()

    return builder.build()
}

val LIBROS = (1..12).map {
    libroRandom()
}

