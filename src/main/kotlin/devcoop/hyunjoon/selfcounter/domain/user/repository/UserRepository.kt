package devcoop.hyunjoon.selfcounter.domain.user.service

import org.springframework.data.jpa.repository.JpaRepository
import devcoop.hyunjoon.selfcounter.domain.user.User

interface UserRepository : JpaRepository<User, String> {
    
}