package devcoop.hyunjoon.selfcounter.domain.user.presentation

import devcoop.hyunjoon.selfcounter.domain.user.service.UserService
import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiPath.COUNTER_USER_API_URL)
class UserController(val userService: UserService) {
    @PutMapping("/pay")
    fun pay(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }
}