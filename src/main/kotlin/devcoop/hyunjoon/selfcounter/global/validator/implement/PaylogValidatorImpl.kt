package devcoop.hyunjoon.selfcounter.global.validator.implement

import devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request.PaylogCreateRequest
import devcoop.hyunjoon.selfcounter.domain.user.service.UserRepository
import devcoop.hyunjoon.selfcounter.global.validator.interfaces.PaylogValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PaylogValidatorImpl(private val userRepository: UserRepository) : PaylogValidator {
    override fun validate(dto: PaylogCreateRequest): ResponseEntity<MutableMap<String, Any>>? {
        val response: MutableMap<String, Any> = mutableMapOf()
        val isUserExist: Boolean = userRepository.existsByUserCode(dto.userCode)
        val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.bssm\\.hs\\.kr$"

        return when {
            dto.payedPoint <= 0 -> {
                response["message"] = "결제금액은 0원 초과여야합니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            dto.userCode.isBlank() -> {
                response["message"] = "사용자 바코드는 필수값입니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            dto.beforePoint - dto.payedPoint != dto.afterPoint -> {
                response["message"] = "결제 전 금액에서 결제금액을 뺀 값이 옳지 않습니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            dto.beforePoint - dto.payedPoint < 0 -> {
                response["message"] = "금액이 부족합니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            !isUserExist -> {
                response["message"] = "존재하지 않는 사용자입니다"
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
            }
            dto.managedEmail.isBlank() || !dto.managedEmail.matches(EMAIL_REGEX.toRegex()) -> {
                response["message"] = "이메일이 비어있거나 옳지 않은 형식입니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            else -> null
        }
    }
}
