package devcoop.hyunjoon.selfcounter.domain.paylog

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.user.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity @Table(name = "occount_payLog")
@EntityListeners(AuditingEntityListener::class)
class PayLog(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var payId: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "userCode", nullable = false)
    private var user: User,
    @CreatedDate var payDate: LocalDateTime = LocalDateTime.now(),
    var payType: String,
    var beforePoint: Int,
    var payedPoint: Int,
    var afterPoint: Int,
    val managedEmail: String = "셀프결제",
    @Enumerated(EnumType.STRING) var eventType: EventType,
) {
    companion object {
        fun create(user: User, payType: String, beforePoint: Int, payedPoint: Int, afterPoint: Int, eventType: EventType): PayLog {
            return PayLog(user = user, payType = payType, beforePoint = beforePoint, payedPoint = payedPoint, afterPoint = afterPoint, eventType = eventType)
        }
    }
}
