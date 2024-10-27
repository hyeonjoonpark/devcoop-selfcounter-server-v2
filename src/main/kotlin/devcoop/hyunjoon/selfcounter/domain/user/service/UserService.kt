package devcoop.hyunjoon.selfcounter.domain.user.service

import devcoop.hyunjoon.selfcounter.domain.user.User
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SigninRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.response.SigninResponse
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.UserPointRequest
import devcoop.hyunjoon.selfcounter.domain.user.security.CustomUserDetailsService
import devcoop.hyunjoon.selfcounter.global.utils.JwtUtil
import devcoop.hyunjoon.selfcounter.global.validator.UserCodeValidator
import devcoop.hyunjoon.selfcounter.global.validator.UserEmailValidator
import devcoop.hyunjoon.selfcounter.global.validator.UserPinValidator
import devcoop.hyunjoon.selfcounter.global.validator.UserValidator
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val customUserDetailsService: CustomUserDetailsService
) {
    val response: MutableMap<String, Any> = mutableMapOf()
    private var accessTokenExpiredTime = 1000 * 60 * 60L
    private var refreshTokenExpiredTime = 1000 * 60 * 60 * 24 * 7L
    
    private val validators: List<UserValidator> = listOf(
        UserCodeValidator(userRepository),
        UserPinValidator(),
        UserEmailValidator()
    )

    @Transactional(rollbackFor = [Exception::class])
    fun deductPoint(dto: UserPointRequest): ResponseEntity<Any> {
        val user = userRepository.findByUserCode(dto.userCode)
            .orElseThrow{throw RuntimeException("존재하지 않는 사용자입니다")}
        if (user.userPoint < dto.totalPrice) {
            // 부족한 금액
            val minusPrice = dto.totalPrice - user.userPoint
            response.put("error", "${minusPrice}원이 부족합니다")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        }
        user.userPoint -= dto.totalPrice
        userRepository.save(user)
        response.put("message", "성공적으로 결제되었습니다")
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

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
            response.put("message", "회원가입에 성공하였습니다")
            ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (error: ValidationException) {
            response.put("error", error.message.toString())
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    fun signIn(dto: SigninRequest): SigninResponse {
        val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(dto.userCode)
         val user = userRepository.findByUserCode(dto.userCode)
             .orElseThrow{throw IllegalStateException("존재하지 않는 사용자입니다")}

        if (!passwordEncoder.matches(dto.userPin, userDetails.password)) {
            throw ValidationException("비밀번호가 일치하지 않습니다.")
        }
        
        val accessToken = jwtUtil.generateToken(userDetails, accessTokenExpiredTime)
        val refreshToken = jwtUtil.generateToken(userDetails, refreshTokenExpiredTime)

        // Refresh token 저장 로직
        user.refreshToken = refreshToken
        userRepository.save(user)

        return SigninResponse(
            message = "로그인을 성공하였습니다",
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

    fun reIssueAccessToken(refreshToken: String): String {
        // refreshToken 유효성 검사
        if (!jwtUtil.validateToken(refreshToken)) {
            throw IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.")
        }

        val userCode = jwtUtil.extractUsername(refreshToken)
        val user = userRepository.findByUserCode(userCode)
            .orElseThrow { IllegalArgumentException("사용자를 찾을 수 없습니다.") }

        // 저장된 리프레시 토큰과 비교
        if (user.refreshToken != refreshToken) {
            throw IllegalArgumentException("저장된 리프레시 토큰과 일치하지 않습니다.")
        }

        return jwtUtil.generateTokenFromRefreshToken(refreshToken, accessTokenExpiredTime)
    }
}
