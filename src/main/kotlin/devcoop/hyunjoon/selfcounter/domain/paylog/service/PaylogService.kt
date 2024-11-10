package devcoop.hyunjoon.selfcounter.domain.paylog.service

import devcoop.hyunjoon.selfcounter.domain.paylog.PayLog
import devcoop.hyunjoon.selfcounter.domain.paylog.repository.PayLogRepository
import devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request.PaylogCreateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PaylogService(private val payLogRepository: PayLogRepository) {
    fun create(dto: PaylogCreateRequest): ResponseEntity<MutableMap<String, Any>> {
        var response: MutableMap<String, Any>  = mutableMapOf()

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
        response.put("message", "성공적으로 결제되었습니다")

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}