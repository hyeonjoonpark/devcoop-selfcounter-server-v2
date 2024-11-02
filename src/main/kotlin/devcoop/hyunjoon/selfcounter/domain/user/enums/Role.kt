package devcoop.hyunjoon.selfcounter.domain.user.enums

enum class Role(val description: String) {
    ROLE_ADMIN("관리자"),
    ROLE_MENBER("사용자"),
    ROLE_DEACTIVATED("비활성화된 사용자")
}