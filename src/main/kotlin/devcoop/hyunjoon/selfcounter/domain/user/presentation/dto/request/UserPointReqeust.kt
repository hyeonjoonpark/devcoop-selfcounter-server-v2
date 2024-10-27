package devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class UserPointReqeust(
    @field:NotBlank(message = "사용자 코드는 필수 값입니다") val userCode: String,
    @field:Positive(message = "최종결제 금액은 0원일 수 없습니다") val totalPrice: Int,
)