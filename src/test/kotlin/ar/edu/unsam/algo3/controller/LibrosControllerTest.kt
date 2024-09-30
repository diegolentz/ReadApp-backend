package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de Libros")
class LibroControllerTest(@Autowired val mockMvc: MockMvc) {


    //Averiguar porque no encuentra los beans
    @Autowired
    lateinit var repo: Repositorio<Libro>

    @BeforeEach
    fun init() {
        repo.clearAll()
    }

    @Test
    fun `puedo mockear una llamada al endpoint via get y me responde correctamente`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/libros"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty)
    }

    @Test
    fun `Si agrego un libro se muestra correctamente`() {
        repo.create(Libro(cantidadPalabras = 999999))
        mockMvc
        .perform(MockMvcRequestBuilders.get("/libros"))
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty)
    }
}