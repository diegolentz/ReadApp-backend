package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.DTO.*
import ar.edu.unsam.algo3.services.ServiceUser
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController

class UserController(val serviceUser: ServiceUser) {

    @GetMapping("/users")
    fun getAllUsersBasic(): List<UserBasicDTO> {
        return serviceUser.getAll().map { it ->
            it.toDTOBasic()
        }
    }
    @GetMapping("/usersTotal")
    fun getAllUsersLength(): Int = serviceUser.getAllSize()

    @GetMapping("/user/basic/{idUsuario}")
    fun getUserBasicByID(@PathVariable idUsuario: String): UserBasicDTO {
        return serviceUser.getByIdBasic(idUsuario)
    }

    @GetMapping("/user/profile/{idUsuario}")
    fun getUserProfileByID(@PathVariable idUsuario: String): UserProfileDTO {
        return serviceUser.getByIdProfile(idUsuario)
    }

    @GetMapping("/user/friends")
    fun getUserFriendsByID(@RequestParam id: Int, @RequestParam muestroAmigos: Boolean): List<UserFriendDTO> {
        return serviceUser.getByIdFriends(id, muestroAmigos)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestBody: LoginRequest): LoginResponse {
        return serviceUser.validateLogin(loginRequestBody)
    }

    @PostMapping("/createAccount")
    fun createAccount(@RequestBody loginRequestBody: CreateAccountRequest): CreateAccountResponse {
        return serviceUser.createAccount(loginRequestBody)
    }

    @PutMapping("/updateInfoUsuario")
    fun updateUsuario(@RequestBody body: UserInfoDTO): UserProfileDTO = serviceUser.updateUserInfo(body)

    @PostMapping("/passwordRecovery")
    fun passwordRecovery(@RequestBody passwordRecoveryBody: PasswordRecoveryRequest): MessageResponse {
        return serviceUser.passwordRecover(passwordRecoveryBody)
    }

    @PutMapping("/updateAmigos")
    fun updateAmigos(@RequestBody body: UpdateFriendsMessage) = serviceUser.updateAmigos(body)

}
