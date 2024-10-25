package devcoop.hyunjoon.selfcounter.domain.user.presentation

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.UserPointRequest
import devcoop.hyunjoon.selfcounter.domain.user.service.UserRepository
import devcoop.hyunjoon.selfcounter.domain.user.service.UserService
import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiPath.COUNTER_USER_API_URL)
class UserController(
    val userService: UserService,
    private val userRepository: UserRepository
) {
    @PutMapping("/pay")
    fun pay(@RequestBody dto: UserPointRequest): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    @PostMapping("/signUp")
    fun signUp(@RequestBody dto: SignupRequest): ResponseEntity<Any> {
        return userService.signUp(dto);
    }
}