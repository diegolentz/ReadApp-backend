import ar.edu.unsam.algo2.readapp.builders.AutorBuilder
import ar.edu.unsam.algo2.readapp.libro.Autor
import ar.edu.unsam.algo2.readapp.libro.Lenguaje
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio

object ServiceAutor {
    val repoAutor: Repositorio<Autor> = Repositorio()

    // Inicialización de datos predeterminados
    init {
        // Crear autores
        val garciaMarquez = AutorBuilder()
            .lenguaNativa(Lenguaje.RUSO)
            .edad(50)
            .nombre("Gabriel")
            .apellido("Garcia Marquez")
            .seudonimo("El Gabo")
            .build()

        val rowling = AutorBuilder()
            .lenguaNativa(Lenguaje.INGLES)
            .edad(42)
            .nombre("J.K.")
            .apellido("Rowling")
            .seudonimo("Robert Galbraith")
            .build()

        val allende = AutorBuilder()
            .lenguaNativa(Lenguaje.ESPANIOL)
            .edad(67)
            .nombre("Isabel")
            .apellido("Allende")
            .seudonimo("La Novelista")
            .build()

        val kafka = AutorBuilder()
            .lenguaNativa(Lenguaje.ALEMAN)
            .edad(55)
            .nombre("Franz")
            .apellido("Kafka")
            .seudonimo("El Existencialista")
            .build()

        val proust = AutorBuilder()
            .lenguaNativa(Lenguaje.FRANCES)
            .edad(48)
            .nombre("Marcel")
            .apellido("Proust")
            .seudonimo("El Memorialista")
            .build()

        // Agregar autores al repositorio
        repoAutor.create(garciaMarquez)
        repoAutor.create(rowling)
        repoAutor.create(allende)
        repoAutor.create(kafka)
        repoAutor.create(proust)
    }
    fun get(): List<Autor> = repoAutor.getAll().toList()

}
