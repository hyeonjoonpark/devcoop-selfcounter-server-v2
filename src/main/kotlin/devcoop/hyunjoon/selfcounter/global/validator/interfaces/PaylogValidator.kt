package devcoop.hyunjoon.selfcounter.global.validator.interfaces

import devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request.PaylogCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
interface PaylogValidator {
    fun validate(dto: PaylogCreateRequest): ResponseEntity<MutableMap<String, Any>>?
}