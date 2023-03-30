package com.chatchatabc.jpademo.application.rest.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtServiceImpl(
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration}")
    private val expiration: String
) : JwtService {
    private val hmac512: Algorithm = Algorithm.HMAC512(secret)
    private val verifier: JWTVerifier = JWT.require(hmac512).build()


    /**
     * Generate JWT Token
     */
    override fun generateToken(user: User): String {
        return JWT.create()
            .withSubject(user.id)
            .withExpiresAt(Date(System.currentTimeMillis() + expiration.toLong()))
            .sign(hmac512)
    }

    /**
     * Validate JWT Token and get ID
     */
    override fun validateTokenAndGetId(token: String): String {
        return try {
            this.verifier.verify(token).subject
        } catch (e: Exception) {
            throw Exception("Invalid token")
        }
    }
}