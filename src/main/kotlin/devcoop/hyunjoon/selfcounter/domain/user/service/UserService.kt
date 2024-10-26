package devcoop.hyunjoon.selfcounter.domain.user.service

import devcoop.hyunjoon.selfcounter.domain.user.User
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SigninResponse
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder // 생성자 주입
) {
    @Transactional(rollbackFor = [Exception::class])
    fun signUp(dto: SignupRequest): ResponseEntity<Any> {
        val isUserExisted: Boolean = userRepository.existsById(dto.userCode)
        if (isUserExisted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 사용자입니다")
        }

        val user: User = User.create(
            id = createUserId(dto.entryYear, dto.category),
            userCode = dto.userCode,
            userName = dto.userName,
            userEmail = dto.userEmail,
            userPassword = passwordEncoder.encode(dto.userPassword),
            userPin = dto.userPin,
            userPoint = 0,
            userFingerPrint = dto.userFingerPrint,
        )
        userRepository.save(user)

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공")
    }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    fun login(): SigninResponse {
        return SigninResponse(
            message = "로그인을 성공하였습니다",
            userCode = "testCode",
            userName = "testName",
            userPoint = 0,
            accessToken = "testAccessToken",
            refreshToken = "testRefreshToken"
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
