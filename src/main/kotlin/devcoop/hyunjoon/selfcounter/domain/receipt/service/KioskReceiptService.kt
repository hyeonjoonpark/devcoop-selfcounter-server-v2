package devcoop.hyunjoon.selfcounter.domain.receipt.service

import devcoop.hyunjoon.selfcounter.domain.receipt.presentation.dto.request.KioskReceiptCreateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class KioskReceiptService {
    fun createKioskReceipt(kioskReceiptCreateRequest: KioskReceiptCreateRequest): ResponseEntity<MutableMap<String, Any>> {
        val response: MutableMap<String, Any> = mutableMapOf()

        response["message"] = "결제 기록이 성공적으로 저장되었습니다"
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}