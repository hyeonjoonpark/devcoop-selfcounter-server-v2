package devcoop.hyunjoon.selfcounter.global.validator

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest

interface UserValidator {
    fun validate(dto: SignupRequest)
}