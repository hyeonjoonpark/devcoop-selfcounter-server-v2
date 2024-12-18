package devcoop.hyunjoon.selfcounter.domain.user.service

import devcoop.hyunjoon.selfcounter.domain.user.User
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SigninRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.response.SigninResponse
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.SignupRequest
import devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request.UserPointRequest
import devcoop.hyunjoon.selfcounter.domain.user.security.CustomUserDetailsService
import devcoop.hyunjoon.selfcounter.global.utils.JwtUtil
import devcoop.hyunjoon.selfcounter.global.validator.implement.UserCodeValidatorImpl
import devcoop.hyunjoon.selfcounter.global.validator.implement.UserEmailValidatorImpl
import devcoop.hyunjoon.selfcounter.global.validator.implement.UserPinValidatorImpl
import devcoop.hyunjoon.selfcounter.global.validator.interfaces.UserValidator
import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.net.URI

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val customUserDetailsService: CustomUserDetailsService,
) {
    val response: MutableMap<String, Any> = mutableMapOf()
    private var ACCESS_TOKEN_EXPIRED_TIME: Long = 1000 * 60 * 60L
    private var REFRESH_TOKEN_EXPIRED_TIME: Long = 1000 * 60 * 60 * 24 * 7L
    
    private val validators: List<UserValidator> = listOf(
        UserCodeValidatorImpl(userRepository),
        UserPinValidatorImpl(),
        UserEmailValidatorImpl()
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
            ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create("/"))
                .body(response)
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
        
        val accessToken = jwtUtil.generateToken(userDetails, ACCESS_TOKEN_EXPIRED_TIME)
        val refreshToken = jwtUtil.generateToken(userDetails, REFRESH_TOKEN_EXPIRED_TIME)

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

    fun createUserId(year: String, category: String): String {
        // 카테고리 코드 설정
        val categoryNumber = when (category) {
            "학생" -> "01"
            "학부모" -> "02"
            "교사" -> "03"
            else -> throw IllegalArgumentException("올바른 구분이 아닙니다.")
        }

        // 해당 연도와 카테고리에 대한 최대 userNumber의 갯수 조회
        val maxNumberInSameYear: Long = userRepository.countByUserNumberContaining(year + categoryNumber)

        // 자동 증가 값은 최대 값 + 1
        val autoIncrementValue = maxNumberInSameYear + 1

        // 새로운 userNumber 생성
        return year + categoryNumber + String.format("%03d", autoIncrementValue.toInt())
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

        return jwtUtil.generateTokenFromRefreshToken(refreshToken, ACCESS_TOKEN_EXPIRED_TIME)
    }
}
