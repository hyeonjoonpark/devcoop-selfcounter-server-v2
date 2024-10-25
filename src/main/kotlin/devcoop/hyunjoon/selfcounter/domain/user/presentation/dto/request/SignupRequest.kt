package devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request

data class SignupRequest(
    val userNumber: String,
    val userCode: String,
    val userName: String,
    val userEmail: String,
    val userPassword: String,
    val userPin: String
)
