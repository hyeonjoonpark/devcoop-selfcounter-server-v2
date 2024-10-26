package devcoop.hyunjoon.selfcounter.domain.user.security

import devcoop.hyunjoon.selfcounter.domain.user.service.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(userCode: String): UserDetails {
        val user = userRepository.findById(userCode)
            .orElseThrow{throw UsernameNotFoundException("존재하지 않는 사용자입니다")}
        return CustomUserDetails(user)
    }
}