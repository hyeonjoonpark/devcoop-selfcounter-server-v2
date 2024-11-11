package devcoop.hyunjoon.selfcounter.domain.paylog.service

import devcoop.hyunjoon.selfcounter.domain.paylog.PayLog
import devcoop.hyunjoon.selfcounter.domain.paylog.repository.PayLogRepository
import devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request.PaylogCreateRequest
import devcoop.hyunjoon.selfcounter.global.validator.PaylogValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PaylogService(
    private val payLogRepository: PayLogRepository,
    private val paylogValidator: PaylogValidator
) {

    fun create(dto: PaylogCreateRequest): ResponseEntity<MutableMap<String, Any>> {
        val response: MutableMap<String, Any> = mutableMapOf()

        val validationResult = paylogValidator.validate(dto)
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
}
