package devcoop.hyunjoon.selfcounter.global.validator.implement

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.global.validator.interfaces.UserValidator
import jakarta.validation.ValidationException

class UserPinValidatorImpl : UserValidator {
    override fun validate(dto: SignupRequest) {
        if (dto.userPin.length < 4) {
            throw ValidationException("핀 번호는 최소 4자리 이상이여야 합니다")
        }

        if (containsForbiddenSequence(dto.userPin)) {
            throw ValidationException("핀 번호에는 같은 숫자가 4개 이상 연속으로 포함될 수 없습니다")
        }
    }

    private fun containsForbiddenSequence(pin: String): Boolean {
        var count = 1
        for (i in 1 until pin.length) {
            if (pin[i] == pin[i - 1]) {
                count++
                if (count >= 4) {
                    return true // 같은 숫자가 4개 이상 연속
                }
            } else {
                count = 1 // 연속성이 끊어지면 카운트 초기화
            }
        }
        return false
    }
}
