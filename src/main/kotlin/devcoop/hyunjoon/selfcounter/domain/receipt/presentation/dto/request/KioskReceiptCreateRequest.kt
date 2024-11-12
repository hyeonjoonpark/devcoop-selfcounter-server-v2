package devcoop.hyunjoon.selfcounter.domain.receipt.presentation.dto.request

import devcoop.hyunjoon.selfcounter.domain.receipt.presentation.dto.KioskItemInfo

data class KioskReceiptCreateRequest(
    val items: MutableList<KioskItemInfo>,
    val userCode: String,
)
