package devcoop.hyunjoon.selfcounter.domain.receipt.presentation

import devcoop.hyunjoon.selfcounter.domain.receipt.presentation.dto.request.KioskReceiptCreateRequest
import devcoop.hyunjoon.selfcounter.domain.receipt.service.KioskReceiptService
import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiPath.COUNTER_RECEIPT_API_URL)
class KioskReceiptController(private val kioskReceiptService: KioskReceiptService) {
    @PostMapping("/create")
    fun createKioskReceipt(@RequestBody kioskReceiptCreateRequest: KioskReceiptCreateRequest): ResponseEntity<MutableMap<String, Any>> {
        return kioskReceiptService.createKioskReceipt();
    }
}