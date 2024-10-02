package ar.edu.unsam.algo3.mock

import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo3.services.ServiceAutor


val autores = ServiceAutor.get()


    val LIBROS = listOf(
        Libro(
            autor = autores.random(),
            cantidadPalabras = 80000,
            cantidadPaginas = 400,
            ediciones = 5,
            ventasSemanales = 300,
            traducciones = (mutableSetOf(Lenguaje.ESPANIOL)),
            titulo = "El mar en calma",
            imagen = "https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg"),
        Libro(
        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "Sombras en la noche",
        imagen = "https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg"),
    Libro(

        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "La ciudad perdida",
        imagen = "https://image.tmdb.org/t/p/original/grEVYkBAVIzQ4JmZ7ydceN9DFQR.jpg"),
    Libro(

        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "El misterio del lago",
        imagen = "https://st.booknet.com/uploads/covers/220/1548248964_73.jpg"),

    Libro(

        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "Un viaje inesperado",
        imagen = "https://http2.mlstatic.com/D_NQ_NP_998326-MLC54028482109_022023-O.webp"),

    Libro(


        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "El último refugio",
        imagen = "https://proassetspdlcom.cdnstatics2.com/usuaris/libros/thumbs/54fbdd8d-9558-4328-bd98-983b52175273/m_175_310/329653_el-ultimo-refugio_9786070768958_3d_202104151753.webp"),

    Libro(

        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "La última frontera",
        imagen = "https://tse2.mm.bing.net/th?id=OIP.hrEnCP84kpA99IFJkR7BywAAAA&pid=Api&P=0&h=180"),

    Libro(

        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "En las Montañas de la Locura",
        imagen = "https://i.pinimg.com/originals/ab/ec/d5/abecd51b79366c089872d1e88e6c7424.jpg"),

    Libro(
         autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "La Llamada de Cthulhu",
        imagen = "https://images.cdn1.buscalibre.com/fit-in/360x360/9f/67/9f672c2b0920c001252d8446a88ce260.jpg"),

    Libro(
        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "El Color que Cayó del Cielo",
        imagen = "https://cdn.kobo.com/book-images/Images/a446b9b8-6f54-4edd-b1de-450d04abaa18/300/300/False/image.jpg"),

    Libro(
        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "Caminos de fuego",
        imagen = "https://media.s-bol.com/qPn8Q9DA0q0/550x827.jpg"),

    Libro(
        autor = autores.random(),
        cantidadPalabras = 75000,
        cantidadPaginas = 350,
        ediciones = 3,
        ventasSemanales = 500,
        traducciones = mutableSetOf(Lenguaje.INGLES, Lenguaje.FRANCES),
        titulo = "La Sombra sobre Innsmouth",
        imagen = "https://www.elejandria.com/covers/La_sombra_sobre_Innsmouth-H._P._Lovecraft-md.png"),

    )



