package devcoop.hyunjoon.selfcounter.domain.user.service

import devcoop.hyunjoon.selfcounter.domain.user.User
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SigninRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.response.SigninResponse
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.global.validator.UserCodeValidator
import devcoop.hyunjoon.selfcounter.global.validator.UserEmailValidator
import devcoop.hyunjoon.selfcounter.global.validator.UserPinValidator
import devcoop.hyunjoon.selfcounter.global.validator.UserValidator
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    private val validators: List<UserValidator> = listOf(
        UserCodeValidator(userRepository),
        UserPinValidator(),
        UserEmailValidator()
    )

    @Transactional(rollbackFor = [Exception::class])
    fun signUp(dto: SignupRequest): ResponseEntity<Any> {
        return try {
            validators.forEach { validator ->
                validator.validate(dto)
            }

            val user: User = User.create(
                id = createUserId(dto.entryYear, dto.category),
                userCode = dto.userCode,
                userName = dto.userName,
                userEmail = dto.userEmail,
                userPassword = passwordEncoder.encode(dto.userPassword),
                userPin = passwordEncoder.encode(dto.userPin),
                userPoint = 0,
                userFingerPrint = dto.userFingerPrint,
            )
            userRepository.save(user)

            ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공")
        } catch (error: ValidationException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.message)
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    fun signIn(dto: SigninRequest): SigninResponse {
        val user: User = userRepository.findById(dto.userCode)
            .orElseThrow { IllegalStateException("존재하지 않는 사용자입니다") }

        if (!passwordEncoder.matches(dto.userPin, user.userPin)) {
            throw ValidationException("비밀번호가 일치하지 않습니다.")
        }

        val accessToken = ""
        val refreshToken = "refresh_token_$user.id"

        return SigninResponse(
            message = "로그인 성공",
            userCode = user.userCode,
            userName = user.userName,
            userPoint = user.userPoint,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun createUserId(year: String?, category: String): String {
        val maxValue: Long = userRepository.count()
        val categoryNumber = when (category) {
            "학생" -> "01"
            "학부모" -> "02"
            "교사" -> "03"
            else -> throw IllegalArgumentException("올바른 구분이 아닙니다.")
        }
        return year + categoryNumber + (maxValue + 1).toString()
    }
}
