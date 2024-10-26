package devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.response

import jakarta.validation.constraints.NotBlank

data class SigninResponse(
    @field:NotBlank val message: String,
    @field:NotBlank val userCode: String,
    @field:NotBlank val userName: String,
    val userPoint: Int,
    @field:NotBlank val accessToken: String,
    @field:NotBlank val refreshToken: String,
)
