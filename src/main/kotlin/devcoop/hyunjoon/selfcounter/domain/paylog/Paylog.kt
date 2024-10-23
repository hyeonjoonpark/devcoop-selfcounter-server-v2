package com.devcoop.kiosk.domain.paylog

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
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private var _user: User,
    @CreatedDate var payDate: LocalDateTime = LocalDateTime.now(),
    var payType: String,
    var beforePoint: Int,
    var payedPoint: Int,
    var afterPoint: Int,
    val managedEmail: String = "셀프결제",
    @Enumerated(EnumType.STRING) var eventType: EventType,
) {
    var user: User
        get() = _user
        set(value) {
            _user = value
        }
}
