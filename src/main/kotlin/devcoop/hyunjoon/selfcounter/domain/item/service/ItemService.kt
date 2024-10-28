package devcoop.hyunjoon.selfcounter.domain.item.service

import devcoop.hyunjoon.selfcounter.domain.item.presentation.dto.response.ItemResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ItemService {
    fun readByBarcode(barcodes: List<String>): ResponseEntity<List<ItemResponse>> {

    }
}