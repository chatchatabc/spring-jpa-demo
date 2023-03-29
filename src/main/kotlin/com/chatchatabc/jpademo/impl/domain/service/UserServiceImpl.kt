package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.application.dto.user.UserProfileUpdateRequest
import com.chatchatabc.jpademo.application.dto.user.UserRegisterRequest
import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserService {
    private val mapper = ModelMapper()

    /**
     * User Register
     */
    override fun register(user: UserRegisterRequest): User {
        // Encrypt password
        val newUser = mapper.map(user, User::class.java)
        newUser.password = passwordEncoder.encode(user.password)
        return userRepository.save(newUser)
    }

    /**
     * Update User Profile
     */
    override fun update(userId: String, user: UserProfileUpdateRequest): User {
        val queriedUser = userRepository.findById(userId)
        if (queriedUser.isEmpty) {
            throw Exception("User not found")
        }

        // Apply update
        queriedUser.get().apply {
            // Update email if not null
            email = user.email ?: email
            // Update username if not null
            username = user.username ?: username
        }
        return userRepository.save(queriedUser.get())

    }

    /**
     * User Login
     */
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Optional<User> = userRepository.findByUsername(username)
        if (user.isEmpty) {
            throw Exception("User not found")
        }
        return org.springframework.security.core.userdetails.User(
            user.get().username,
            user.get().password,
            user.get().isEnabled,
            user.get().isAccountNonExpired,
            user.get().isCredentialsNonExpired,
            user.get().isAccountNonLocked,
            user.get().authorities
        )
    }
}