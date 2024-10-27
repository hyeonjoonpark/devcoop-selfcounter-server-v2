package devcoop.hyunjoon.selfcounter.domain.user.presentation

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SigninRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.response.SigninResponse
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.UserPointRequest
import devcoop.hyunjoon.selfcounter.domain.user.service.UserRepository
import devcoop.hyunjoon.selfcounter.domain.user.service.UserService
import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
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
    companion object {
        private const val USER_POINT = "/point"
        private const val AUTH = "/auth"
    }

    @PutMapping(USER_POINT + "/deduct")
    fun pay(@RequestBody dto: UserPointRequest): ResponseEntity<Any> {
        return userService.deductPoint(dto)
    }

    @PostMapping(AUTH + "/signUp")
    fun signUp(@RequestBody dto: SignupRequest): ResponseEntity<Any> {
        return userService.signUp(dto);
    }

    @PostMapping(AUTH + "/signIn")
    fun signIn(@RequestBody dto: SigninRequest): SigninResponse {
        return userService.signIn(dto)
    }
}
