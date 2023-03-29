package com.chatchatabc.jpademo.domain.service

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
    fun validateTokenAndGetIds(token: String): String;
}