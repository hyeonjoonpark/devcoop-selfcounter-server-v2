package devcoop.hyunjoon.selfcounter.domain.paylog

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity @Table(name = "occount_payLog") @DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
class PayLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var payId: Long = 0L,
    var userCode: String,
    @CreatedDate var payDate: LocalDateTime = LocalDateTime.now(),
    var payType: String,
    var beforePoint: Int,
    var payedPoint: Int,
    var afterPoint: Int,
    val managedEmail: String = "셀프결제",
    @Enumerated(EnumType.STRING) var eventType: EventType,
) {

}
