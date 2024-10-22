package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo2.readapp.repositorios.Repositorio
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.mock.USERS
import ar.edu.unsam.algo3.mock.adrian

import org.junit.jupiter.api.BeforeAll

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de Usuarios")
class RecommendationControllerTest(@Autowired val mockMvc: MockMvc) {

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

}