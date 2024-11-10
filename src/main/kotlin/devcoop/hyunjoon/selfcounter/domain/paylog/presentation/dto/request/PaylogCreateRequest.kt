package devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.paylog.PayLog
import java.time.LocalDateTime

data class PaylogCreateRequest(
    val beforePoint: Int,
    val afterPoint: Int,
    val userCode: String,
    val payedPoint: Int,
    val payType: String,
    val managedEmail: String,
    val eventType: EventType,
) {
    fun toEntity(
        beforePoint: Int,
        afterPoint: Int,
        userCode: String,
        payedPoint: Int,
        payType: String,
        managedEmail: String,
        eventType: EventType
    ): PayLog {
        return PayLog(
            beforePoint = beforePoint,
            afterPoint = afterPoint,
            userCode = userCode,
            payedPoint = payedPoint,
            payType = payType,
            managedEmail = managedEmail,
            eventType = eventType,
        )
    }

}
