package com.chatchatabc.jpademo.domain.service

import com.chatchatabc.jpademo.application.dto.user.UserRegisterRequest
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.stereotype.Service

@Service
interface UserService {

    /**
     * User Register
     */
    fun register(user: UserRegisterRequest): User
}