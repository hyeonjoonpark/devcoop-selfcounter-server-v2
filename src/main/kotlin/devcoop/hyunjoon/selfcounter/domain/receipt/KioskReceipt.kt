package devcoop.hyunjoon.selfcounter.domain.receipt

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.item.enums.SaleType
import devcoop.hyunjoon.selfcounter.domain.item.Item
@Entity
@Table(name = "occount_kioskReceipts")
@EntityListeners(AuditingEntityListener::class)
class KioskReceipt private constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val receiptId: Int, // 거래식별용 id

    @OneToMany(mappedBy = "receipt", cascade = [CascadeType.ALL], orphanRemoval = true) 
    @JoinColumn(name = "items", nullable = false)
    private val _items: MutableList<Item> = mutableListOf(),
    val userCode: String, // 사용자 바코드
    @Enumerated(EnumType.STRING) val saleType: SaleType, // 결제 타입 (0: 정상 결제, 1: 환불 결제 등)
    @Enumerated(EnumType.STRING) val eventType: EventType // 이벤트 여부 ('ONE_PLUS_ONE', 'NONE' 등)
) {
    @CreatedDate var saleDate: LocalDateTime = LocalDateTime.now() // 거래 발생 시간
        protected set

    val items: List<Item>
        get() = _items.toList()

    fun addItem(item: Item) {
        item.receipt = this
        _items.add(item)
    }

    companion object {
        @JvmStatic
        fun create(userCode: String, saleType: SaleType, eventType: EventType): KioskReceipt {
            return KioskReceipt(0, mutableListOf(), userCode, saleType, eventType)
        }
    }
}
