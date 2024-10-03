package ar.edu.unsam.algo3.controller
import ar.edu.unsam.algo2.readapp.usuario.Usuario
import ar.edu.unsam.algo3.services.ServiceUser

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController

class UserController(val serviceUser: ServiceUser) {

    @GetMapping("/users")
    fun getAllUsers(): List<Usuario> = serviceUser.getAll()

    @GetMapping("/user/basic/{id}")
    fun getUserBasicByID(@PathVariable id: Int) = serviceUser.getByIdBasic(id)

    @GetMapping("/user/profile/{id}")
    fun getUserProfileByID(@PathVariable id: Int) = serviceUser.getByIdProfile(id)
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
