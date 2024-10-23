package devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class UserPointRequest(
    @NotBlank(message = "바코드번호는 필수 입력값입니다") val userCode: String,
    @field:Positive(message = "최종 결제금액은 0보다 커야합니다") val totalPrice: Int,
)
