package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.user.UserRegisterRequest
import com.chatchatabc.jpademo.application.dto.user.UserRegisterResponse
import com.chatchatabc.jpademo.domain.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {
    /**
     * TODO: User Login
     */

    /**
     * User Registration
     */
    @PostMapping("/register")
    fun register(
        @RequestBody userRegisterRequest: UserRegisterRequest
    ): ResponseEntity<UserRegisterResponse> {
        return try {
            val newUser = userService.register(userRegisterRequest)
            return ResponseEntity.ok(UserRegisterResponse(newUser, null))
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