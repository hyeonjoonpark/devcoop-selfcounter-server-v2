package devcoop.hyunjoon.selfcounter.domain.paylog.presentation.dto.request

import devcoop.hyunjoon.selfcounter.domain.item.enums.EventType
import devcoop.hyunjoon.selfcounter.domain.paylog.PayLog
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class PaylogCreateRequest(
    @Positive(message = "결제금액은 음수일 수 없습니다") val beforePoint: Int,
    @Positive(message = "결제금액은 음수일 수 없습니다") val afterPoint: Int,
    @NotBlank(message = "사용자코드는 필수값입니다") val userCode: String,
    @Positive(message = "결제금액은 음수일 수 없습니다") val payedPoint: Int,
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
