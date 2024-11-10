package devcoop.hyunjoon.selfcounter.domain.item.presentation

import devcoop.hyunjoon.selfcounter.domain.item.presentation.dto.response.ItemResponse
import devcoop.hyunjoon.selfcounter.domain.item.service.ItemService
import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiPath.COUNTER_ITEM_API_URL)
class ItemController(
    val itemService: ItemService,
) {
    @GetMapping("/read")
    fun readItem(@RequestBody barcodes: List<String>): ResponseEntity<MutableList<ItemResponse>> {
        return itemService.readByBarcode(barcodes)
    }

    @GetMapping("/event-items/read")
    fun readEventItems(): ResponseEntity<MutableList<ItemResponse>> {
        return itemService.readEventItems()
    }
}