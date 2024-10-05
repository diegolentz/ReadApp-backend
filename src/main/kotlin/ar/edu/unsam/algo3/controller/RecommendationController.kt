
package ar.edu.unsam.algo3.controller
import ar.edu.unsam.algo3.DTO.RecomendacionDTO

import ar.edu.unsam.algo3.services.ServiceRecommendation

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController

class RecommendationController(val serviceRecommendation: ServiceRecommendation) {

    @GetMapping("/recommendations")
    fun getRecommendations(): List<RecomendacionDTO> = serviceRecommendation.getAll()

   @GetMapping("/recommendations/{id}")
   fun getRecommendationById(@PathVariable id: Int) : RecomendacionDTO = serviceRecommendation.getByIdDTO(id)

//    @PostMapping("/recommendations/{id}")
//    fun createRecommendation(@RequestBody recommendationDTO: RecommendationDTO): Recomendacion =
//        serviceRecommendation.createRecommendation(recommendationDTO.convertir())
//
//    @PutMapping("/recommendations/{id}")
//    fun updateRecommendation(@RequestBody newRecommendation: RecommendationDTO): Recomendacion =
//        serviceRecommendation.updateRecommendation(newRecommendation.convertir())
//
//    @DeleteMapping("/recommendations{id}")
//    fun deleteRecommendation(@PathVariable id: Int) = serviceRecommendation.deleteRecommendation(id)


}
