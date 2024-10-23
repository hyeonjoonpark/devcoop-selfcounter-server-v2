package devcoop.hyunjoon.selfcounter.global.common.exception

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL
import org.springframework.http.HttpStatus

data class GlobalResponse(
    val status: HttpStatus,
    val data: Any,
    @field:NotBlank(message = "응답메세지는 필수값입니다") val message: String,
    @field:URL(message = "올바른 형식의 URL이 아닙니다") val redirectURL: String
)
