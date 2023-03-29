package com.chatchatabc.jpademojava.domain.service;

import com.chatchatabc.jpademojava.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    /**
     * Generate JWT token
     *
     * @param user
     * @return
     */
    String generateToken(User user);

    /**
     * Validate token and get user id
     *
     * @param token
     * @return
     */
    String validateTokenAndGetId(String token) throws Exception;
}
