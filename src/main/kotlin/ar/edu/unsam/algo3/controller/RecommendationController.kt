
package ar.edu.unsam.algo3.controller
import ar.edu.unsam.algo3.DTO.RecomendacionDTO
import ar.edu.unsam.algo3.DTO.RecomendacionEditarDTO
import ar.edu.unsam.algo3.DTO.ValoracionDTO

import ar.edu.unsam.algo3.services.ServiceRecommendation

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController

class RecommendationController(val serviceRecommendation: ServiceRecommendation) {

    @GetMapping("/recommendations")
    fun getRecommendations(): List<RecomendacionDTO> = serviceRecommendation.getAll()

   @GetMapping("/recommendations/{id}")
   fun getRecommendationById(@PathVariable id: Int) : RecomendacionDTO = serviceRecommendation.getByIdDTO(id)

    @PutMapping("/recommendations")
    fun updateRecommendation(@RequestBody newRecommendation: RecomendacionEditarDTO): RecomendacionEditarDTO =
        serviceRecommendation.updateRecommendation(newRecommendation)

    @PutMapping("/recommendations/{id}")
    fun createValoracion(@RequestBody valoracion:ValoracionDTO,@PathVariable id: Int ): ValoracionDTO =
        serviceRecommendation.createValoracion(valoracion,id)

//    @DeleteMapping("/recommendations{id}")
//    fun deleteRecommendation(@PathVariable id: Int) = serviceRecommendation.deleteRecommendation(id)

    @GetMapping("/recommendations/filter")
    fun getRecommendationFilter(@RequestParam filtro: String) : List<RecomendacionDTO> =
        serviceRecommendation.getWithFilter(filtro)
}
