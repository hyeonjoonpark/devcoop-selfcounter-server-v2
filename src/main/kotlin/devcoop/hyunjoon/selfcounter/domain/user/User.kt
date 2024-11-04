package devcoop.hyunjoon.selfcounter.domain.user
\
import devcoop.hyunjoon.selfcounter.domain.user.enums.Role
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity(name = "common_user")
@DynamicUpdate
class User private constructor(
    id: String,
    userCode: String,
    userName: String,
    userEmail: String,
    userPassword: String,
    userPin: String,
    userPoint: Int,
    userFingerPrint: String,
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
    @Column(name = "userFingerPrint") var userFingerPrint: String = userFingerPrint
        protected set
    @Enumerated(value = EnumType.STRING) @Column(name = "roles")
    var role: Role = Role.ROLE_MENBER
        protected set
    @Column(name = "refreshToken")
    lateinit var refreshToken: String

    companion object {
        fun create(
            id: String,
            userCode: String,
            userName: String,
            userEmail: String,
            userPassword: String,
            userPin: String,
            userPoint: Int,
            userFingerPrint: String,
        ): User {
            return User(
                id = id, // id 생성로직 확인필요
                userCode = userCode,
                userName = userName,
                userEmail = userEmail,
                userPassword = userPassword,
                userPin = userPin,
                userPoint = userPoint,
                userFingerPrint = userFingerPrint,
            )
        }
    }
}
