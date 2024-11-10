package devcoop.hyunjoon.selfcounter.domain.item.service

import devcoop.hyunjoon.selfcounter.domain.item.Item
import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.item.presentation.dto.response.ItemResponse
import devcoop.hyunjoon.selfcounter.domain.item.repository.ItemRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {

    @Transactional(rollbackFor = [Exception::class], readOnly = true)
    fun readByBarcode(barcodes: List<String>): ResponseEntity<MutableList<ItemResponse>> {
        val itemResponses: MutableList<ItemResponse> = mutableListOf()

        for (barcode in barcodes) {
            // 바코드로 아이템 조회
            val item: Item = itemRepository.findByItemCode(barcode)
                .orElseThrow { RuntimeException("존재하지 않는 상품입니다") }

            // 이벤트 타입 결정
            val eventType = if (item.event == EventType.ONE_PLUS_ONE) {
                EventType.ONE_PLUS_ONE
            } else {
                EventType.NONE
            }

            // ItemResponse 생성
            val response = ItemResponse(
                itemName = item.itemName,
                itemPrice = item.itemPrice,
                itemQuantity = if (eventType == EventType.NONE) 1 else 2,
                eventType = eventType
            )
            itemResponses.add(response)
        }

        return ResponseEntity.status(HttpStatus.OK).body(itemResponses)
    }

    @Transactional(rollbackFor = [Exception::class], readOnly = true)
    fun readEventItems(): ResponseEntity<MutableList<ItemResponse>> {
        // EventType이 NONE이 아닌 아이템 조회
        val eventItems: MutableList<Item> = itemRepository.findByEventTypeNot(EventType.NONE)

        // ItemResponse로 변환
        val itemResponses: MutableList<ItemResponse> = eventItems.map {
            item -> ItemResponse(
                itemName = item.itemName,
                itemPrice = item.itemPrice,
                itemQuantity = 1,
                eventType = EventType.ONE_PLUS_ONE
            )
        }.toMutableList()

        return ResponseEntity.status(HttpStatus.OK).body(itemResponses)
    }
}