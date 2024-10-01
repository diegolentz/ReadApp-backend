package ar.edu.unsam.algo3.controller
import ar.edu.unsam.algo2.readapp.features.Recomendacion
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.services.ServiceUser

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController

class UserController(val serviceUser: ServiceUser) {

    @GetMapping("/users")
    fun getRecommendations(): List<Usuario> = serviceUser.getAll()

    @GetMapping("/users/{id}")
    fun getRecommendationById(@PathVariable id: Int) = serviceUser.getById(id)
//
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
