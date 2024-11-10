package devcoop.hyunjoon.selfcounter.domain.paylog.presentation

import devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request.PaylogCreateRequest
import devcoop.hyunjoon.selfcounter.domain.paylog.service.PaylogService
import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiPath.COUNTER_PAYLOG_API_URL)
class PaylogController(private val paylogService: PaylogService) {
    @PostMapping("/create")
    fun createPaylog(@RequestBody request: PaylogCreateRequest): ResponseEntity<MutableMap<String, Any>> {
        return paylogService.create(request);
    }
}