package com.chatchatabc.jpademojava.application.rest.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.chatchatabc.jpademojava.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private String secret;
    private String expiration;
    private Algorithm hmac512;
    private JWTVerifier verifier;


    public JwtServiceImpl(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") String expiration
    ) {
        this.secret = secret;
        this.expiration = expiration;
        hmac512 = Algorithm.HMAC512(secret);
        verifier = JWT.require(hmac512).build();
    }


    /**
     * Generate JWT token
     *
     * @param user
     * @return
     */
    @Override
    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .sign(hmac512);
    }

    /**
     * Validate token and get user id
     *
     * @param token
     * @return
     */
    @Override
    public String validateTokenAndGetId(String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
