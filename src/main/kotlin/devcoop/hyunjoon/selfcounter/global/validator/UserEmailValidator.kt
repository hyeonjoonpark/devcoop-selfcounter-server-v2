package devcoop.hyunjoon.selfcounter.global.validator

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import jakarta.validation.ValidationException

class UserEmailValidator: UserValidator {
    val bssmEmalRegex = "@bssm.hs.kr"

    override fun validate(dto: SignupRequest) {
        if(!dto.userEmail.endsWith(bssmEmalRegex)) {
            throw ValidationException("이메일 형식은 $bssmEmalRegex 어야 합니다")
        }
    }
}