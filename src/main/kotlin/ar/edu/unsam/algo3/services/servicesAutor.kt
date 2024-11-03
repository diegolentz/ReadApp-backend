package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo3.DTO.AuthorEditDTO
import ar.edu.unsam.algo3.DTO.AutorDTO
import org.springframework.stereotype.Service


@Service
object ServiceAutor {
    val repoAutor: Repositorio<Autor> = Repositorio()

    // Inicialización de datos predeterminados
//    init {
//        AUTOR.forEach(
//            { autor -> repoAutor.create(autor) }
//        )
//    }
    fun get(): List<Autor> = repoAutor.getAll().toList()

    fun getAll(): List<AutorDTO> {
        var libros = repoAutor.getAll().toList()
        return libros.map { it: Autor -> it.toDTO() }
    }

    fun getById(autorID: Int): Autor = repoAutor.getByID(autorID)

    fun editAutor(autorID: Int): AuthorEditDTO {
        val autor = repoAutor.getByID(autorID)
        return autor.toEditDTO()
    }

    fun actualizarAuthor(author : AutorDTO) : Autor  {
        val confirmEdit = this.getById(author.id)
         confirmEdit.nombre = author.nombre
        confirmEdit.apellido = author.apellido
        confirmEdit.lenguaNativa = author.nacionalidad

        return confirmEdit
    }

    fun borrarAutor(idAutor: Int): Autor {
        val autor = getById(idAutor)
        repoAutor.delete(autor)
        return autor
    }
}
