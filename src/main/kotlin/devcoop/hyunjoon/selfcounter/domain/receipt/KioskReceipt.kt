package devcoop.hyunjoon.selfcounter.domain.receipt

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.item.Item
import devcoop.hyunjoon.selfcounter.domain.item.enums.SaleType

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
<<<<<<< HEAD
    @Enumerated(EnumType.STRING) val saleType: SaleType, // 결제 타입('정상결제', '환불결제')
=======
    @Enumerated(EnumType.STRING) val saleType: SaleType, // 결제 타입('정상결제', '반품결제')
>>>>>>> origin/main
    @Enumerated(EnumType.STRING) val eventType: EventType // 이벤트 여부 ('ONE_PLUS_ONE', 'NONE')
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
