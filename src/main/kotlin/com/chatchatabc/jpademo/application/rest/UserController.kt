package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.user.UserProfileResponse
import com.chatchatabc.jpademo.application.dto.user.UserProfileUpdateRequest
import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userRepository: UserRepository,
    private val userService: UserService
) {
    /**
     * User Profile
     */
    @GetMapping("/profile")
    fun profile(): ResponseEntity<UserProfileResponse> {
        return try {
            // Get id from security context
            val principal = SecurityContextHolder.getContext().authentication.principal as User
            val user = userRepository.findById(principal.id)
            ResponseEntity.ok(UserProfileResponse(user.get(), null))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(UserProfileResponse(null, ErrorContent("User Profile Error", e.message)))
        }
    }

    /**
     * View profile of other user
     */
    @GetMapping("/profile/view/{username}")
    fun viewProfile(
        @PathVariable username: String
    ): ResponseEntity<UserProfileResponse> {
        return try {
            val user = userRepository.findByUsername(username)
            if (user.isEmpty) {
                throw Exception("User not found")
            }
            ResponseEntity.ok(UserProfileResponse(user.get(), null))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(UserProfileResponse(null, ErrorContent("User Profile Error", e.message)))
        }
    }

    /**
     * Update User Profile
     */
    @Transactional
    @PutMapping("/profile/update")
    fun updateProfile(
        @RequestBody user: UserProfileUpdateRequest
    ): ResponseEntity<UserProfileResponse> {
        return try {
            // Get id from security context
            val principal = SecurityContextHolder.getContext().authentication.principal as User
            val user = userService.update(principal.id, user)
            ResponseEntity.ok(UserProfileResponse(user, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(UserProfileResponse(null, ErrorContent("User Profile Error", e.message)))
        }
    }

    /**
     * TODO: Update User Password
     */
}