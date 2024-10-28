package devcoop.hyunjoon.selfcounter.domain.user.security.filter

import devcoop.hyunjoon.selfcounter.global.utils.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {
    private val publicPaths = listOf("/kiosk/v2/user/auth/signIn", "/kiosk/v2/user/auth/signUp")

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val servletPath = request.servletPath

        // 공개 경로 체크
        if (publicPaths.any { servletPath.startsWith(it) }) {
            filterChain.doFilter(request, response)
            return
        }

        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing authorization header")
            return
        }

        try {
            val token = authorization.split(" ")[1]

            if (jwtUtil.isTokenExpired(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 만료되었습니다")
                return
            }

            val userCode = jwtUtil.extractUsername(token)

            if (userCode.isBlank()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "존재하지 않는 사용자 바코드입니다")
                return
            }

            val authenticationToken = UsernamePasswordAuthenticationToken(
                userCode,
                null,
                listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authenticationToken

            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error processing JWT token")
        }
    }
}