package devcoop.hyunjoon.selfcounter.domain.user.service

import devcoop.hyunjoon.selfcounter.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<User, String> {
    fun findByUserCode(userCode: String): Optional<User>

    fun countByUserNumberContaining(prefix: String): Long

    fun existsByUserCode(userCode: String): Boolean

}