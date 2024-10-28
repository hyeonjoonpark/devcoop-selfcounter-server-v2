package devcoop.hyunjoon.selfcounter.domain.item.service

import devcoop.hyunjoon.selfcounter.domain.item.Item
import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.item.presentation.dto.response.ItemResponse
import devcoop.hyunjoon.selfcounter.domain.item.repository.ItemRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    var quantity = 1
    var eventType = EventType.NONE
    fun readByBarcode(barcodes: List<String>): ResponseEntity<MutableList<ItemResponse>> {
        val itemResponses: MutableList<ItemResponse> = mutableListOf()

        for (barcode in barcodes) {
            val item: Item = itemRepository.findByItemCode(barcode)
                .orElseThrow{RuntimeException("존재하지 않는 상품입니다")}

            if(item.event == EventType.ONE_PLUS_ONE) {
                quantity = 2
                eventType = EventType.ONE_PLUS_ONE
            }

            val response = ItemResponse(
                itemName = item.itemName,
                itemPrice = item.itemPrice,
                itemQuantity = item.itemQuantity,
                eventType = eventType
            )
            itemResponses.add(response)
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemResponses)
    }
}