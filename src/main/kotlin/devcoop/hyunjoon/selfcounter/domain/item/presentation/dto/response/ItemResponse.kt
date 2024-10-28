package devcoop.hyunjoon.selfcounter.domain.item.presentation.dto.response

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType

data class ItemResponse(
    val itemName: String,
    val itemPrice: Int,
    val itemQuantity: Int,
    val eventType: EventType,
)
