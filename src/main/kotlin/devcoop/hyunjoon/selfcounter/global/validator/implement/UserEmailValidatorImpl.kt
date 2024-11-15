package devcoop.hyunjoon.selfcounter.global.validator.implement

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.global.validator.interfaces.UserValidator
import jakarta.validation.ValidationException

class UserEmailValidatorImpl: UserValidator {
    val bssmEmalRegex = "@bssm.hs.kr"

    override fun validate(dto: SignupRequest) {
        if(!dto.userEmail.endsWith(bssmEmalRegex)) {
            throw ValidationException("이메일 형식은 $bssmEmalRegex 어야 합니다")
        }
    }
}