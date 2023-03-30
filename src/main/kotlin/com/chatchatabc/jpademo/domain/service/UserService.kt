package com.chatchatabc.jpademo.domain.service

import com.chatchatabc.jpademo.application.dto.user.UserProfileUpdateRequest
import com.chatchatabc.jpademo.application.dto.user.UserRegisterRequest
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
interface UserService : UserDetailsService {

    /**
     * User Register
     */
    fun register(user: UserRegisterRequest): User

    /**
     * Update User Profile
     */
    @Transactional
    fun update(userId: String, user: UserProfileUpdateRequest): User

    /**
     * Update User Password
     */
    @Transactional
    fun updatePassword(userId: String, oldPassword: String, newPassword: String): User
}