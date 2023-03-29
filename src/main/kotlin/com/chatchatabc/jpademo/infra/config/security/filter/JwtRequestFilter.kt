package com.chatchatabc.jpademo.infra.config.security.filter

import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.Optional

@Component
class JwtRequestFilter (
    private val jwtService: JwtService,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(JwtRequestFilter::class.java)

    /**
     * Filter the request and validate the token
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Get authorization header and validate
        val header = request.getHeader("Authorization")

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            logRequest(request, response)
            return
        }

        val token: String = header.substring(7)
        val userId: String = jwtService.validateTokenAndGetId(token)

        // Get the user from the database
        val user: Optional<User> = userRepository.findById(userId)

        // If the user is not found
        if (user.isEmpty) {
            filterChain.doFilter(request, response)
            logRequest(request, response)
            return
        }

        val authentication = UsernamePasswordAuthenticationToken(
            user.get(),
            null,
            user.get().authorities
        )

        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication

        // Continue flow with authenticated user
        filterChain.doFilter(request, response)
        logRequest(request, response, userId)
    }

    /**
     * Log the request path and the response code
     */
    fun logRequest(request: HttpServletRequest, response: HttpServletResponse) {
        // Log the path of the request
        log.info("Request path: ${request.method} ${request.requestURL} from ${request.remoteAddr} with code ${response.status}")
    }

    /**
     * Log the request path and the response code with user id
     */
    fun logRequest(request: HttpServletRequest, response: HttpServletResponse, userId: String) {
        // Log the path of the request
        log.info("Request path: ${request.method} ${request.requestURL} User ID $userId from ${request.remoteAddr} with code ${response.status}")
    }
}