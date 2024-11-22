package ar.edu.unsam.algo3.services
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo3.DTO.AuthorCreateDTO
import ar.edu.unsam.algo3.DTO.AuthorDTO
import ar.edu.unsam.algo3.DTO.AuthorEditDTO
import ar.edu.unsam.algo3.DTO.LenguajeDTO
import excepciones.BusinessException
import org.springframework.stereotype.Service


@Service
object ServiceAutor {
    val repoAutor: Repositorio<Autor> = Repositorio()

    fun get(): List<Autor> = repoAutor.getAll().toList()

    fun getById(autorID: Int): Autor = repoAutor.getByID(autorID)

    fun getAll(): List<AuthorDTO> {
        var autores = repoAutor.getAll().toList()
        if (autores.isEmpty()) {
            throw BusinessException("Failed to fetch authors")
        }
        return autores.map { it: Autor -> it.toDTO() }
    }



    fun getAllForBooks(): List<AuthorEditDTO> {
        var autores = repoAutor.getAll().toList()
        if (autores.isEmpty()) {
            throw BusinessException("Failed to fetch authors")
        }
        return autores.map { it: Autor -> it.toEditDTO() }
    }


    fun obtenerLenguajes(): LenguajeDTO {

        val lenguajes = Lenguaje.values().toList()
            if (lenguajes.isEmpty()) {
                throw BusinessException("Failed to fetch languages")
            }
        return LenguajeDTO(lenguajes)
    }

    fun editAutor(autorID: Int): AuthorDTO {
     try {
        val autor = repoAutor.getByID(autorID)
        return autor.toDTO()

        } catch (e: Exception) {
            throw BusinessException("Author not found")
        }
    }

    fun actualizarAuthor(author : AuthorEditDTO) : Autor  {
        try {
        val autor = this.getById(author.id)
        val autorActualizado = autor.actualizar(author)

        return autorActualizado
        }catch (e: Exception) {
            throw BusinessException("Author cant be updated")
        }
    }

    fun borrarAutor(idAutor: Int): Autor {
        try {
        val autor = getById(idAutor)
        repoAutor.delete(autor)
        return autor
        }catch (e: Exception) {
            throw BusinessException("Author cant be deleted")
        }
    }

    fun crearAutor(autor: AuthorCreateDTO): Boolean  {
        try {
       val autorNuevo : Boolean = repoAutor.create(
           Autor(lenguaNativa = autor.nacionalidad,
               apellido = autor.apellido,
               nombre = autor.nombre,
           ))
        return autorNuevo
        } catch (e: Exception) {
            throw BusinessException("Author cant be created")
        }
    }

    fun getFilter(filter: String): List<AuthorDTO> {
        var autores = repoAutor.getAll().toList()
        if (autores.isEmpty()) {
            throw BusinessException("Failed to fetch authors")
        }
        return autores.filter { it.nombre.contains(filter, ignoreCase = true) || it.apellido.contains(filter , ignoreCase = true) }
            .map { it: Autor -> it.toDTO() }
    }
}
