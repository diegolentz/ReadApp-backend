package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo2.readapp.libro.Libro
import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.mock.LIBROS
import ar.edu.unsam.algo3.mock.USERS
import ar.edu.unsam.algo3.services.ServiceLibros
import ar.edu.unsam.algo3.services.ServiceLibros.repoLibro
import ar.edu.unsam.algo3.services.ServiceUser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de Libros")
class LibroControllerTest(@Autowired val mockMvc: MockMvc) {


    //Averiguar porque no encuentra los beans
    @Autowired
    lateinit var repoUsuario: Repositorio<Usuario>

//    @BeforeEach
//    fun init() {
//        repoUsuario.clearAll()
//        val usuario = USERS[0]
//        repoUsuario.create(usuario)
//
//
//        ServiceUser.loggedUserId = 1
//    }

    @Test
    fun `puedo mockear una llamada al endpoint via get y me responde correctamente`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/librosSearch"))
            .andExpect(MockMvcResultMatchers.status().isOk)

    }

    @Test
    fun `realizo un get de libros y obtengo los de la app`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/librosSearch"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.length()").value(LIBROS.size)
            ) // Verifica la longitud del array
    }

    @Test
    fun `Si agrego un libro se muestra correctamente en la lista de libros para leer`() {

        // Verifica que el usuario creado tenga el ID 1
        val usuarioCreado = repoUsuario.getByID(1)
        assertNotNull(usuarioCreado)
        assertEquals(1, usuarioCreado.id)
        //ya tiene libros para leer incorporados

        assertTrue(ServiceLibros.obtenerLibros(false).size == 6)

        //  solicitud PUT para agregar libros
        mockMvc.perform(
            MockMvcRequestBuilders.put("/agregarLibroEstado")
                .contentType("application/json")
                .content(
                    """
                {
                    "idUser": 1,
                    "estado": false,
                    "idLibro": [10]
                }
                """.trimIndent()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
        assertTrue(usuarioCreado.librosALeer.size == 7)

    }

    @Test
    fun `puedo quitar un libro del usuario `() {
        val usuarioCreado = repoUsuario.getByID(1)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/eliminarLibroEstado")
                .contentType("application/json")
                .content(
                    """
                {
                    "estado": true,
                    "idLibro": [1]
                }
                """.trimIndent()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
        assertTrue(usuarioCreado.librosALeer.size == 6)

    }

    @Test
    fun `puedo obtener libros segun estado`() {
        // Prepara el usuario de prueba
        val usuarioCreado = repoUsuario.getByID(1)

        // Realiza la solicitud GET para obtener los libros según el estado
        mockMvc.perform(
            MockMvcRequestBuilders.get("/obtenerlibroEstado")
                .contentType("application/json")
                .param("idUser", "1")  // Pasa el idUser como parámetro
                .param("estado", "false")  // Pasa el estado como parámetro
        ).andExpect(MockMvcResultMatchers.status().isOk)  // Verifica que el status sea 200 OK
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(7))
    }
//    @GetMapping("/add-Books")
//    fun agregarALeeer(@RequestParam idUser: Int): List<LibroDTO> = serviceLibros.paraLeer(idUser)

    @Test
    fun `libros que puedo agregar en para leer`() {
        // Prepara el usuario de prueba
        val usuarioCreado = repoUsuario.getByID(1)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/add-Books")
                .contentType("application/json")
                .param("idUser", "1")  // Pasa el idUser como parámetro
        ).andExpect(MockMvcResultMatchers.status().isOk)  // Verifica que el status sea 200 OK
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
    }

    @Test
    fun `puedo obtener libros filtrados`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/librosSearch/filter")
                .contentType("application/json")
                .param("filtro", "Caminos de fuego")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1)
        )
    }
}





