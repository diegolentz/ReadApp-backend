package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.DTO.CreateAccountRequest
import ar.edu.unsam.algo3.DTO.CreateAccountResponse
import ar.edu.unsam.algo3.DTO.LoginRequest
import ar.edu.unsam.algo3.mock.USERS
import com.fasterxml.jackson.databind.ObjectMapper
import excepciones.loginErrorMessage
import excepciones.usernameInvalidMessage
import org.json.JSONObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de Usuarios")
class UserControllerTest(@Autowired val mockMvc: MockMvc) {

    //Averiguar porque no encuentra los beans
    @Autowired
    lateinit var repo: Repositorio<Usuario>

    @BeforeEach
    fun init() {
        repo.clearAll()
    }

    @Test
    fun `Llamo al endpoint user-basic-{id} y responde con informacion basica del usuario, OK`() {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/user/basic/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(USERS[0].nombre))
    }

    @Test
    fun `Llamo al endpoint user-basic-{id}, con ID fuera de rango, devuelve error, NOT_FOUND`() {
        val invalidID:Int = -1
        val errorMessage = mockMvc
            .perform(MockMvcRequestBuilders.get("/user/basic/$invalidID"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn().resolvedException?.message
        assertEquals(errorMessage, "No existe objeto con ID: $invalidID")
    }

    @Test
    fun `Llamo al endpoint user-basic-{STRING}, devuelve error, BAD_REQUEST`() {
        val invalidID:String = "saraza"
        val errorMessage = mockMvc
            .perform(MockMvcRequestBuilders.get("/user/basic/$invalidID"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn().resolvedException?.message
        assertEquals(errorMessage, "$invalidID no es un entero, ingrese un id válido")
    }

    @Test
    fun `Login valido`() {
        //Se asume usuario registrado
        val loguinRequest = LoginRequest(
            username = "adrian",
            password = "adrian"
        )
        val json = ObjectMapper().writeValueAsString(loguinRequest)
        //Como el login devuelve el ID de usuario, puedo verificar que sea el correcto
        //Para el caso adrian, adrian, el id seria el 5, acorde al orden que se agregan al repositorio
        //Diego => 1 , Valen => 2, Delfi => 3, Pica => 4, Adrian => 5
        val responseBody = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
        val responseJSON = JSONObject(responseBody)

        val loguedUserID = responseJSON.getInt("userID")
        assertEquals(loguedUserID, 5)
    }

    @Test
    fun `Login NO valido, USUARIO INCORRECTO`() {

        val loguinRequest = LoginRequest(
            username = "ramon",
            password = "adrian"
        )

        val json = ObjectMapper().writeValueAsString(loguinRequest)

        val errorMessage = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn().resolvedException?.message

        assertEquals(errorMessage, loginErrorMessage)
    }

    @Test
    fun `Login NO valido, CONTRASENIA INCORRECTA`() {
        val loguinRequest = LoginRequest(
            username = "adrian",
            password = "ramon"
        )

        val json = ObjectMapper().writeValueAsString(loguinRequest)

        val errorMessage = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn().resolvedException?.message

        assertEquals(errorMessage, loginErrorMessage)
    }

    @Test
    fun `Crear cuenta, OK`() {
        //Se asume usuario registrado
        val createAccountRequest = CreateAccountRequest(
            username = "zaboomafoo",
            password = "zaboomafoo",
            name = "Zabu Mafu",
            email = "ZabuMafu@zaboomafoo"
        )
        val json = ObjectMapper().writeValueAsString(createAccountRequest)
        val response = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/createAccount")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        val responseJSON = JSONObject(response)

        val message = responseJSON.get("message")
        assertEquals(message, CreateAccountResponse().message)
    }

    @Test
    fun `Crear cuenta, USUARIO NO DISPONIBLE`() {
        //Se asume usuario registrado
        val loguinRequest = CreateAccountRequest(
            username = "adrian",
            password = "zaboomafoo",
            name = "Zabu Mafu",
            email = "ZabuMafu@zaboomafoo"
        )
        val json = ObjectMapper().writeValueAsString(loguinRequest)
        val errorMessage = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/createAccount")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn().resolvedException?.message

        assertEquals(errorMessage, usernameInvalidMessage)
    }
}