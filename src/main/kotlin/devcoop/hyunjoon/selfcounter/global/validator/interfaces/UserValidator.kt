package devcoop.hyunjoon.selfcounter.global.validator.interfaces

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest

interface UserValidator {
    fun validate(dto: SignupRequest)
}