package devcoop.hyunjoon.selfcounter.domain.user.presentation.dto.request

data class SignupRequest(
    val userCode: String,
    val userName: String,
    val userEmail: String,
    val userPassword: String,
    val userPin: String,
    val userFingerPrint: String,

    val entryYear: String, // 입학년도
    // 학생, 학부모, 교사
    val category: String
)
