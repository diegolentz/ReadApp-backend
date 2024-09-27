import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.libro.Libro


class LibroDTO(
    val id_autor: Int,
    var cantidadPalabras: Int,
    var cantidadPaginas: Int,
    var ediciones: Int,
    var ventasSemanales: Int,
    var traducciones: MutableSet<Lenguaje>,
    var titulo: String,
    var id: Int = -1,
) {
    var autor: Autor = buscarAutor()

    fun buscarAutor(): Autor {
        val autorExistente = ServiceAutor.get().find { it.id == id_autor }
        if (autorExistente != null) {
            return autorExistente
        }
//        Acá debería devolver una excepción avisandoq ue el autor no existe, que hay que crearlo primero o revisar que su ide este bien
        return Autor()
    }

    fun convertir(): Libro =
        LibroBuilder().autor(autor)
            .cantidadPalabras(cantidadPalabras)
            .cantidadPaginas(cantidadPaginas)
            .ediciones(ediciones)
            .ventasSemanales(ventasSemanales)
            .traducciones(traducciones)
            .titulo(titulo)
            .id(id)
            .build()
}
