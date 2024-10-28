package devcoop.hyunjoon.selfcounter.domain.item.repository

import devcoop.hyunjoon.selfcounter.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository: JpaRepository<Item, Long> {

}