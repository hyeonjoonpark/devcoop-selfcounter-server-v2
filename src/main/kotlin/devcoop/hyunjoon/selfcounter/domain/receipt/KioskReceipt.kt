package devcoop.hyunjoon.selfcounter.domain.receipt

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.item.enums.SaleType
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity @Table(name = "occount_kioskReceipts") @DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
class KioskReceipt(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val receiptId: Int, // 거래식별용 id
    val itemCode: String,
    val tradedPoint: Int, // 거래 가격
    var itemName: String, // 아이템 이름
    val saleQty: Int, // 거래 수량
    @CreatedDate val saleDate: LocalDateTime = LocalDateTime.now(), // 거래 발생 시간
    @Enumerated(EnumType.STRING) val saleType: SaleType, // 결제 타입 (NORMAL, REFUND)
    @Enumerated(EnumType.STRING) val eventType: EventType // 이벤트 여부 ('ONE_PLUS_ONE', 'NONE')
) {

}
