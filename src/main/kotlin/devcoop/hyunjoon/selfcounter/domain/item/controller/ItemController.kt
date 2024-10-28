package devcoop.hyunjoon.selfcounter.domain.item.controller

import devcoop.hyunjoon.selfcounter.domain.item.repository.ItemRepository
import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ApiPath.COUNTER_ITEM_API_URL)
class ItemController(
    val itemRepository: ItemRepository
) {
    
}