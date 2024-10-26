package devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request

data class SigninRequest(
    val userCode: String,
    val userPin: String,
)
