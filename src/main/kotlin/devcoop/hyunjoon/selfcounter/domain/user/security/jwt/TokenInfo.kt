package devcoop.hyunjoon.selfcounter.domain.user.security.jwt

data class TokenInfo(
    val grantType: TokenGrantType = TokenGrantType.BEARER,
    val accessToken: String,
    val refreshToken: String,
) {
    enum class TokenGrantType(val value: String) {
        BEARER("Bearer")
    }

    enum class TokenType(val value: String) {
        ACCESS("access"),
        REFRESH("refresh")
    }
}
