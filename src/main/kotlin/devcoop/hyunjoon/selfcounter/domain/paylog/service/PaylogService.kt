package devcoop.hyunjoon.selfcounter.domain.paylog.service

import devcoop.hyunjoon.selfcounter.domain.paylog.PayLog
import devcoop.hyunjoon.selfcounter.domain.paylog.repository.PayLogRepository
import devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request.PaylogCreateRequest
import devcoop.hyunjoon.selfcounter.domain.user.service.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PaylogService(
    private val payLogRepository: PayLogRepository,
    private val userRepository: UserRepository
) {

    fun create(dto: PaylogCreateRequest): ResponseEntity<MutableMap<String, Any>> {
        val response: MutableMap<String, Any> = mutableMapOf()

        val validationResult = validate(dto)
        if (validationResult != null) {
            return validationResult
        }

        // Paylog 생성로직
        val payLog: PayLog = dto.toEntity(
            dto.beforePoint,
            dto.afterPoint,
            dto.userCode,
            dto.payedPoint,
            dto.payType,
            dto.managedEmail,
            dto.eventType
        )
        payLogRepository.save(payLog)
        response["message"] = "성공적으로 결제되었습니다"

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    private fun validate(dto: PaylogCreateRequest): ResponseEntity<MutableMap<String, Any>>? {
        val response: MutableMap<String, Any> = mutableMapOf()
        val isUserExist: Boolean = userRepository.existsByUserCode(dto.userCode)
        val EMAIL_PREFIX = "@bssm.hs.kr"

        return when {
            // 결제금액 검증로직
            dto.payedPoint <= 0 -> {
                response["message"] = "결제금액은 0원 초과여야합니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            // 사용자 바코드 검증로직
            dto.userCode.isBlank() -> {
                response["message"] = "사용자 바코드는 필수값입니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            // 결제 전, 결제금액, 결제 후 금액 검증 로직
            dto.beforePoint - dto.payedPoint != dto.afterPoint -> {
                response["message"] = "결제 전 금액에서 결제금액을 뺀 값이 옳지 않습니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            // 결제금액 검증로직
            dto.beforePoint - dto.payedPoint < 0 -> {
                response["message"] = "금액이 부족합니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            // 사용자 존재 여부 검증로직
            !isUserExist -> {
                response["message"] = "존재하지 않는 사용자입니다"
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
            }
            // manager 이메일 검증로직
            dto.managedEmail.isBlank() || dto.managedEmail.matches(Regex(EMAIL_PREFIX)) -> {
                response["message"] = "이메일이 비어있거나 옳지 않은 형식입니다"
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
            }
            else -> null
        }
    }
}