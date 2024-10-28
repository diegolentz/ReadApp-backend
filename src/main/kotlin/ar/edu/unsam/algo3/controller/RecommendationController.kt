package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.DTO.*
import ar.edu.unsam.algo3.services.ServiceRecommendation

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:5173"])
@RestController

class RecommendationController(val serviceRecommendation: ServiceRecommendation) {

    @GetMapping("/recommendations")
    fun getAllRecommendations(): List<RecommendationCardDTO> = serviceRecommendation.getAll()

    @GetMapping("/recommendationsTotal")
    fun getAllRecommendationsLength(): Int = serviceRecommendation.getAllSize()

    @GetMapping("/recommendations/{id}")
    fun getRecommendationById(@PathVariable id: Int): RecomendacionDTO = serviceRecommendation.getByIdDTO(id)

    @PostMapping("/recommendations")
    fun createRecommendation(@RequestBody recommendationCrearDTO: RecomendacionCrearDTO): RecomendacionDTO =
        serviceRecommendation.createRecommendation(recommendationCrearDTO)

    @PutMapping("/recommendations")
    fun updateRecommendation(@RequestBody newRecommendation: RecomendacionEditarDTO): RecomendacionEditarDTO =
        serviceRecommendation.updateRecommendation(newRecommendation)

    @PutMapping("/recommendations/{id}")
    fun createValoracion(@RequestBody valoracion: ValoracionDTO, @PathVariable id: Int): ValoracionDTO =
        serviceRecommendation.createValoracion(valoracion, id)

    @GetMapping("/recommendations/filter")
    fun getRecommendationFilter(@RequestParam filtro: String): List<RecommendationCardDTO> =
        serviceRecommendation.getWithFilter(filtro)

    //Recomendaciones de home o privadas
    @GetMapping("/recommendationsLoggedUser")
    fun getUserRecommendations(@RequestParam privada: Boolean): List<RecommendationCardDTO> =
        serviceRecommendation.getUserRecommendations(privada)

    @DeleteMapping("/delete/recommendation/{id}")
    fun deleteRecommendation(@PathVariable id: Int): MessageResponse =
        serviceRecommendation.deleteRecommendation(id)

    @GetMapping("/recommendationsToValue")
    fun getRecommendationsToValue(): List<RecommendationCardDTO> =
        serviceRecommendation.getRecommendationsToValue()

    @GetMapping("/recommendationsByProfile")
    fun getRecommendationsByProfile(): List<RecommendationCardDTO> =
        serviceRecommendation.getRecommendationsByProfile()

    @PutMapping("/addToValueLater/{id}")
    fun createValoracion(@PathVariable id: Int): MessageResponse =
        serviceRecommendation.addToValueLater(id)
}
