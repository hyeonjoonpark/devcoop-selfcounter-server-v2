package devcoop.hyunjoon.selfcounter.domain.item.repository

import devcoop.hyunjoon.selfcounter.domain.item.Item
import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ItemRepository: JpaRepository<Item, Long> {
    fun findByItemCode(itemCode: String): Optional<Item>

    fun findByEventTypeNot(eventType: EventType): MutableList<Item>
}