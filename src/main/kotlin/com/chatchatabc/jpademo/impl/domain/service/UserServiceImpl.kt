package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.application.dto.user.UserRegisterRequest
import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
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
}