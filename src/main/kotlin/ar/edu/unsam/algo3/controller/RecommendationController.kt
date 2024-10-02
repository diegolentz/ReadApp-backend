//
//package ar.edu.unsam.algo3.controller
//import ar.edu.unsam.algo2.readapp.features.Recomendacion
//import ar.edu.unsam.algo3.dominio.RecommendationDTO
//import ar.edu.unsam.algo3.services.ServiceRecommendation
//
//import org.springframework.web.bind.annotation.*
//
//@CrossOrigin(origins = ["http://localhost:4200"])
//@RestController
//
//class RecommendationController(val serviceRecommendation: ServiceRecommendation) {
//
//    @GetMapping("/recommendations")
//    fun getRecommendations(): List<Recomendacion> = serviceRecommendation.getAll()
//
//   @GetMapping("/recommendations/{id}")
//   fun getRecommendationById(@PathVariable id: Int) = serviceRecommendation.getById(id)
//
////    @PostMapping("/recommendations/{id}")
////    fun createRecommendation(@RequestBody recommendationDTO: RecommendationDTO): Recomendacion =
////        serviceRecommendation.createRecommendation(recommendationDTO.convertir())
////
////    @PutMapping("/recommendations/{id}")
////    fun updateRecommendation(@RequestBody newRecommendation: RecommendationDTO): Recomendacion =
////        serviceRecommendation.updateRecommendation(newRecommendation.convertir())
////
////    @DeleteMapping("/recommendations{id}")
////    fun deleteRecommendation(@PathVariable id: Int) = serviceRecommendation.deleteRecommendation(id)
//
//
//}
