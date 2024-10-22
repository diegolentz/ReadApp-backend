package ar.edu.unsam.algo3.mock

import LibroBuilder
import ar.edu.unsam.algo2.readapp.builders.AutorBuilder
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceAutor
import kotlin.random.Random


val autores = ServiceAutor.get()

var autorPreferidoPica: Autor = AutorBuilder().build()

var libroAutorUnico: Libro = LibroBuilder().autor(autorPreferidoPica)
    .build()

var libro_1: Libro = LibroBuilder()
    .titulo("La sombra sobre Innsmouth").autor(autores.random())
    .cantidadPaginas(850).cantidadPalabras(200 * 850)
    .ventasSemanales(1200).edicionesRandom().traduccionesRandom()
    .imagen("https://www.elejandria.com/covers/La_sombra_sobre_Innsmouth-H._P._Lovecraft-md.png")
    .build()

var libro_2: Libro = LibroBuilder()
    .titulo("Caminos de fuego").autor(autores.random())
    .cantidadPaginas(600).cantidadPalabras(200 * 600)
    .ventasSemanales(950).edicionesRandom().traduccionesRandom()
    .imagen("https://media.s-bol.com/qPn8Q9DA0q0/550x827.jpg")
    .build()

var libro_3: Libro = LibroBuilder()
    .titulo("Sombras en la noche").autor(autores.random())
    .cantidadPaginas(400).cantidadPalabras(200 * 400)
    .ventasSemanales(2500).edicionesRandom().traduccionesRandom()
    .imagen("https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg")
    .build()

var libro_4: Libro = LibroBuilder()
    .titulo("El mar en calma y viaje feliz").autor(autores.random())
    .cantidadPaginas(950).cantidadPalabras(200 * 950)
    .ventasSemanales(3100).edicionesRandom().traduccionesRandom()
    .imagen("https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg")
    .build()

var libro_5: Libro = LibroBuilder()
    .titulo("Ultima frontera").autor(autores.random())
    .cantidadPaginas(700).cantidadPalabras(200 * 700)
    .ventasSemanales(1800).edicionesRandom().traduccionesRandom()
    .imagen("https://tse2.mm.bing.net/th?id=OIP.hrEnCP84kpA99IFJkR7BywAAAA&pid=Api&P=0&h=180")
    .build()

var libro_6: Libro = LibroBuilder()
    .titulo("El ultimo refugio").autor(autores.random())
    .cantidadPaginas(530).cantidadPalabras(200 * 530)
    .ventasSemanales(1450).edicionesRandom().traduccionesRandom()
    .imagen("https://proassetspdlcom.cdnstatics2.com/usuaris/libros/thumbs/54fbdd8d-9558-4328-bd98-983b52175273/m_175_310/329653_el-ultimo-refugio_9786070768958_3d_202104151753.webp")
    .build()

var libro_7: Libro = LibroBuilder()
    .titulo("Un viaje inesperado").autor(AUTOR[0])
    .cantidadPaginas(390).cantidadPalabras(200 * 390)
    .ventasSemanales(2200).edicionesRandom().traduccionesRandom()
    .imagen("https://http2.mlstatic.com/D_NQ_NP_998326-MLC54028482109_022023-O.webp")
    .build()

var libro_8: Libro = LibroBuilder()
    .titulo("El misterio del lago").autor(autores.random())
    .cantidadPaginas(750).cantidadPalabras(200 * 750)
    .ventasSemanales(2700).edicionesRandom().traduccionesRandom()
    .imagen("https://st.booknet.com/uploads/covers/220/1548248964_73.jpg")
    .build()

var libro_9: Libro = LibroBuilder()
    .titulo("La ciudad perdida").autor(autores.random())
    .cantidadPaginas(450).cantidadPalabras(200 * 450)
    .ventasSemanales(1950).edicionesRandom().traduccionesRandom()
    .imagen("https://image.tmdb.org/t/p/original/grEVYkBAVIzQ4JmZ7ydceN9DFQR.jpg")
    .build()

var libro_10: Libro = LibroBuilder()
    .titulo("Sombras de la noche II").autor(autores.random())
    .cantidadPaginas(880).cantidadPalabras(200 * 880)
    .ventasSemanales(3300).edicionesRandom().traduccionesRandom()
    .imagen("https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg")
    .build()

var libro_11: Libro = LibroBuilder()
    .titulo("El mar en calma y viaje feliz II").autor(autores.random())
    .cantidadPaginas(680).cantidadPalabras(200 * 680)
    .ventasSemanales(1250).edicionesRandom().traduccionesRandom()
    .imagen("https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg")
    .build()

var libro_12: Libro = LibroBuilder()
    .titulo("El bosque negro").autor(autores.random())
    .cantidadPaginas(520).cantidadPalabras(200 * 520)
    .ventasSemanales(1400).edicionesRandom().traduccionesRandom()
    .imagen("https://i.pinimg.com/originals/ab/ec/d5/abecd51b79366c089872d1e88e6c7424.jpg")
    .build()

val LIBROS = mutableListOf(
    libro_1, libro_2, libro_3, libro_4,
    libro_5, libro_6, libro_7, libro_8,
    libro_9, libro_10, libro_11, libro_12)