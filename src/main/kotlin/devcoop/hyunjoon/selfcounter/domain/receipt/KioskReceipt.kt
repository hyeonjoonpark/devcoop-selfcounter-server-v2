package devcoop.hyunjoon.selfcounter.domain.receipt

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.item.enums.SaleType
import devcoop.hyunjoon.selfcounter.domain.item.Item
import devcoop.hyunjoon.selfcounter.domain.user.User

@Entity
@Table(name = "occount_kioskReceipts")
class KioskReceipt(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val receiptId: Int, // 거래식별용 id
    @ManyToMany
    @JoinTable(
        name = "receipt_item",
        joinColumns = [JoinColumn(name = "receipt_id")],
        inverseJoinColumns = [JoinColumn(name = "itemCode")]
    )
    val items: MutableList<Item> = mutableListOf(),
    val itemCode: String,
    val tradedPoint: Int, // 거래 가격
    var itemName: String, // 아이템 이름
    val saleQty: Int, // 거래 수량
    @CreatedDate val saleDate: LocalDateTime = LocalDateTime.now(), // 거래 발생 시간
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "userCode", nullable = false)
    val userCode: User, // 사용자 바코드
    @Enumerated(EnumType.STRING) val saleType: SaleType, // 결제 타입 (NORMAL, REFUND)
    @Enumerated(EnumType.STRING) val eventType: EventType // 이벤트 여부 ('ONE_PLUS_ONE', 'NONE')
) {

}
