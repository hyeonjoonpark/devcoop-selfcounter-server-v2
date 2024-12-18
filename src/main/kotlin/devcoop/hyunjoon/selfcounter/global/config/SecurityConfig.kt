package devcoop.hyunjoon.selfcounter.global.config

import devcoop.hyunjoon.selfcounter.global.utils.ApiPath
import devcoop.hyunjoon.selfcounter.domain.user.security.filter.JwtFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configure(http) }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests { request ->
                request
                    // 회원가입 API
                    .requestMatchers(HttpMethod.POST, ApiPath.COUNTER_USER_API_URL + "/auth/signUp").permitAll()
                    // 로그인 API
                    .requestMatchers(HttpMethod.POST, ApiPath.COUNTER_USER_API_URL + "/auth/signIn").permitAll()
                    // 결제 (유저 포인트 차감 API)
                    .requestMatchers(HttpMethod.PUT, ApiPath.COUNTER_USER_API_URL + "/point/deduct").authenticated()
                    // 상품 항목조회
                    .requestMatchers(HttpMethod.GET, ApiPath.COUNTER_ITEM_API_URL + "/read").authenticated()
                    .anyRequest().permitAll()
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
