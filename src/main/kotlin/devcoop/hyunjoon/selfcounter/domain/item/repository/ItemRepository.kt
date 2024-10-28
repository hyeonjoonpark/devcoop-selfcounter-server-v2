package devcoop.hyunjoon.selfcounter.domain.item.repository

import devcoop.hyunjoon.selfcounter.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ItemRepository: JpaRepository<Item, Long> {
    fun findByItemCode(itemCode: String): Optional<Item>
}