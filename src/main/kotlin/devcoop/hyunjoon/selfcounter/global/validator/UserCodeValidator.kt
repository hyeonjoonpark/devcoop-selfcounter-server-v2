package devcoop.hyunjoon.selfcounter.global.validator

import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.domain.user.service.UserRepository

class UserCodeValidator(private val userRepository: UserRepository) : UserValidator {
    override fun validate(dto: SignupRequest) {
        if (userRepository.existsById(dto.userCode)) {
            throw RuntimeException("이미 존재하는 사용자입니다")
        }
    }
}
