package devcoop.hyunjoon.selfcounter.domain.user

import com.devcoop.kiosk.domain.paylog.PayLog
import devcoop.hyunjoon.selfcounter.domain.user.enums.Role
import jakarta.persistence.*

@Entity(name = "common_user")
class User private constructor(
    id: String,
    userCode: String,
    userName: String,
    userEmail: String,
    userPassword: String,
    userPin: String,
    userPoint: Int,
    userFingerPrint: String
) {
    @Id @Column(name = "userNumber") var id: String = id
        protected set
    @Column(name = "userCode") var userCode: String = userCode
        protected set
    @Column(name = "userName") var userName: String = userName
        protected set
    @Column(name = "userEmail") var userEmail: String = userEmail
        protected set
    @Column(name = "userPassword") var userPassword: String = userPassword
        protected set
    @Column(name = "userPin") var userPin: String = userPin
        protected set
    @Column(name = "userPoint") var userPoint: Int = userPoint
        protected set
    @Column(name = "userFingerPrint") var userFingerPrint: String = userFingerPrint
        protected set
    @Enumerated(value = EnumType.STRING) @Column(name = "role")
    var role: Role = Role.ROLE_USER
        protected set
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val _payLogs: MutableList<PayLog> = mutableListOf()

    companion object {
        fun create(
            id: String,
            userCode: String,
            userName: String,
            userEmail: String,
            userPassword: String,
            userPin: String,
            userPoint: Int,
            userFingerPrint: String
        ): User {
            return User(id, userCode, userName, userEmail, userPassword, userPin, userPoint, userFingerPrint)
        }
    }
}
