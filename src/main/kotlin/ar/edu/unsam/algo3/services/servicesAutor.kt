package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo3.DTO.AuthorCreateDTO
import ar.edu.unsam.algo3.DTO.AuthorDTO
import ar.edu.unsam.algo3.DTO.AuthorEditDTO
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

    fun getAll(): List<AuthorDTO> {
        var autores = repoAutor.getAll().toList()

        return autores.map { it: Autor -> it.toDTO() }
    }

    fun getById(autorID: Int): Autor = repoAutor.getByID(autorID)

    fun editAutor(autorID: Int): AuthorDTO {
        val autor = repoAutor.getByID(autorID)
        return autor.toDTO()
    }

    fun actualizarAuthor(author : AuthorEditDTO) : Autor  {
        val autor = this.getById(author.id)
        val autorActualizado = autor.actualizar(author)

        return autorActualizado
    }

    fun borrarAutor(idAutor: Int): Autor {
        val autor = getById(idAutor)
        repoAutor.delete(autor)
        return autor
    }

    fun crearAutor(autor: AuthorCreateDTO): Boolean  {
       val autorNuevo : Boolean = repoAutor.create(
           Autor(lenguaNativa = autor.nacionalidad,
               apellido = autor.apellido,
               nombre = autor.nombre,
           ))

        return autorNuevo
    }
}
