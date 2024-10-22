package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.DTO.*
import ar.edu.unsam.algo3.mock.USERS
import ar.edu.unsam.algo3.mock.adrian
import com.fasterxml.jackson.databind.ObjectMapper
import excepciones.*
import org.json.JSONObject
import org.junit.jupiter.api.BeforeAll
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

    companion object{
        var repo = Repositorio<Usuario>()
        @JvmStatic
        @BeforeAll
        fun initializer(){
              repo.run {
                  adrian
              }
        }
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
            .andExpect(MockMvcResultMatchers.status().isNotFound)
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
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn().resolvedException?.message

        assertEquals(errorMessage, loginErrorMessage)
    }

    @Test
    fun  `Login valido`() {
        initializer()
        val loguinRequest = LoginRequest(
            username = "adrian",
            password = "adrian"
        )
        val json = ObjectMapper().writeValueAsString(loguinRequest)
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
        assertEquals(5, loguedUserID)
    }

    @Test
    fun `Crear cuenta, USUARIO NO DISPONIBLE`() {
        repo.create(adrian)
        val createAccountRequest = CreateAccountRequest(
            username = "adrian",
            password = "zaboomafoo",
            name = "Zabu Mafu",
            email = "ZabuMafu@zaboomafoo"
        )
        val json = ObjectMapper().writeValueAsString(createAccountRequest)
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

    @Test
    fun `Crear cuenta, MAIL NO DISPONIBLE`() {
        val createAccountRequest = CreateAccountRequest(
            username = "zaboomafoo",
            password = "zaboomafoo",
            name = "Zabu Mafu",
            email = "adrian@hotmail.com"
        )
        val json = ObjectMapper().writeValueAsString(createAccountRequest)
        val errorMessage = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/createAccount")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn().resolvedException?.message

        assertEquals(errorMessage, emailInvalidMessage)
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
    fun `Recuperar contrasenia, EMAIL INCORRECTO`() {
        //Se asume usuario registrado
        val loguinRequest = PasswordRecoveryRequest(
            username = "adrian",
            email = "adrian@pepe",
            newPassword = "pepe"
        )
        val json = ObjectMapper().writeValueAsString(loguinRequest)
        val errorMessage = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/passwordRecovery")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn().resolvedException?.message

        assertEquals(passwordRecoveryErrorMessage, errorMessage)
    }

    @Test
    fun `Recuperar contrasenia, USUARIO INCORRECTO`() {
        //Se asume usuario registrado
        val loguinRequest = PasswordRecoveryRequest(
            username = "zaboomafoo",
            email = "adrian@hotmail.com",
            newPassword = "pepe"
        )
        val json = ObjectMapper().writeValueAsString(loguinRequest)
        val errorMessage = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/passwordRecovery")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
            .andReturn().resolvedException?.message

        assertEquals(errorMessage, passwordRecoveryErrorMessage)
    }

    @Test
    fun `Recuperar contrasenia, OK`() {
        //Se asume usuario registrado
        val request = PasswordRecoveryRequest(
            username = "adrian",
            email = "adrian@hotmail.com",
            newPassword = "pepe"
        )
        val json = ObjectMapper().writeValueAsString(request)
        val response = mockMvc
            .perform(
                MockMvcRequestBuilders.post("/passwordRecovery")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString

        val responseJSON = JSONObject(response)

        val message = responseJSON.get("message")
        assertEquals(message, MessageResponse(passwordChangeOK).message)
    }
}