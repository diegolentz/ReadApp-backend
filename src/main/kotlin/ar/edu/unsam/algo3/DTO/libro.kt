import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro

class LibroDTO(
    val autor: String,  // Puede ser directamente el nombre o el objeto Autor
    var cantidadPalabras: Int,
    var cantidadPaginas: Int,
    var ediciones: Int,
    var ventasSemanales: Int,
    var traducciones: MutableSet<Lenguaje>,
    var titulo: String,
    var id: Int,
    var imagen: String
) {
    // Se pasa el autor como parámetro externo
    fun fromDTO(libroDTO: LibroDTO, autor: Autor): Libro {
        return Libro(
            autor = autor,  // Usamos el autor que nos pasaron
            cantidadPalabras = libroDTO.cantidadPalabras,
            cantidadPaginas = libroDTO.cantidadPaginas,
            ediciones = libroDTO.ediciones,
            ventasSemanales = libroDTO.ventasSemanales,
            traducciones = libroDTO.traducciones,
            titulo = libroDTO.titulo,
            imagen = libroDTO.imagen,

        ).apply { id = libroDTO.id }
    }
}

class BookWithBooleansDTO(
    val id: Int,  // Puede ser directamente el nombre o el objeto Autor
    var title: String,
    var author: String,
    var numberOfPages: Int,
    var numberOfWords: Int,
    var translations: MutableSet<Lenguaje>,
    var isBestSeller: Boolean,
    var isChallenging: Boolean,
    var image: String
) {
    // Se pasa el autor como parámetro externo
    fun fromDTO(libroDTO: LibroDTO, autor: Autor): Libro {
        return Libro(
            autor = autor,  // Usamos el autor que nos pasaron
            cantidadPalabras = libroDTO.cantidadPalabras,
            cantidadPaginas = libroDTO.cantidadPaginas,
            ediciones = libroDTO.ediciones,
            ventasSemanales = libroDTO.ventasSemanales,
            traducciones = libroDTO.traducciones,
            titulo = libroDTO.titulo,
            imagen = libroDTO.imagen,

            ).apply { id = libroDTO.id }
    }
}