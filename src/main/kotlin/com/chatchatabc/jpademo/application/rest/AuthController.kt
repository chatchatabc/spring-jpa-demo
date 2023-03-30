package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.user.UserLoginRequest
import com.chatchatabc.jpademo.application.dto.user.UserLoginResponse
import com.chatchatabc.jpademo.application.dto.user.UserRegisterRequest
import com.chatchatabc.jpademo.application.dto.user.UserRegisterResponse
import com.chatchatabc.jpademo.application.rest.jwt.JwtService
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {
    /**
     * User Login
     */
    @PostMapping("/login")
    fun login(
        @RequestBody userLoginRequest: UserLoginRequest
    ): ResponseEntity<UserLoginResponse> {
        return try {
            val queriedUser = userRepository.findByUsername(userLoginRequest.username)
            if (queriedUser.isEmpty) {
                throw Exception("User not found")
            }

            // Authenticate user
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    userLoginRequest.username,
                    userLoginRequest.password
                )
            )

            val token: String = jwtService.generateToken(queriedUser.get())
            val headers = HttpHeaders()
            headers.set("X-Access-Token", token)
            ResponseEntity.ok().headers(headers).body(UserLoginResponse(queriedUser.get(), null))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(UserLoginResponse(null, ErrorContent("User Login Error", e.message)))
        }
    }

    /**
     * User Registration
     */
    @PostMapping("/register")
    fun register(
        @RequestBody userRegisterRequest: UserRegisterRequest
    ): ResponseEntity<UserRegisterResponse> {
        return try {
            val newUser = userService.register(userRegisterRequest)
            return ResponseEntity.status(HttpStatus.CREATED).body(UserRegisterResponse(newUser, null))
        } catch (e: Exception) {
            // TODO: Catch appropriate exception message
            ResponseEntity.badRequest()
                .body(UserRegisterResponse(null, ErrorContent("User Registration Error", e.message)))
        }
    }

    /**
     * TODO: Check if username is available
     */

    /**
     * TODO: Check if email is available
     */
}