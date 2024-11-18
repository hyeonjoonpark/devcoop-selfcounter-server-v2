package devcoop.hyunjoon.selfcounter.domain.receipt.presentation.dto.request

import devcoop.hyunjoon.selfcounter.domain.receipt.presentation.dto.KioskItemInfo
import jakarta.validation.constraints.NotBlank

data class KioskReceiptCreateRequest(
    val items: MutableList<KioskItemInfo>,
    @NotBlank(message = "학생 바코드는 필수값입니다") val userCode: String,
)
