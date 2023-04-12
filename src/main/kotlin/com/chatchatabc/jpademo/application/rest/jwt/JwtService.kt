package com.chatchatabc.jpademo.application.rest.jwt

import com.chatchatabc.jpademo.domain.model.User
import org.springframework.stereotype.Service

@Service
interface JwtService {
    /**
     * Generate JWT Token
     */
    fun generateToken(user: User): String;

    /**
     * Validate JWT Token and get ID
     */
    fun validateTokenAndGetId(token: String): String;
}