package devcoop.hyunjoon.selfcounter.domain.user.service

import org.springframework.data.jpa.repository.JpaRepository
import devcoop.hyunjoon.selfcounter.domain.user.User
import java.util.Optional

interface UserRepository : JpaRepository<User, String> {
    fun findByUserCode(userCode: String): Optional<User>

    fun countByUserNumberContaining(prefix: String): Long
}