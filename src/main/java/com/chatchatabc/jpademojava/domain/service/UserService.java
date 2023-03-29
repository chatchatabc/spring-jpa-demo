package com.chatchatabc.jpademojava.domain.service;

import com.chatchatabc.jpademojava.application.dto.user.UserPasswordUpdateRequest;
import com.chatchatabc.jpademojava.application.dto.user.UserProfileUpdateRequest;
import com.chatchatabc.jpademojava.application.dto.user.UserRegisterRequest;
import com.chatchatabc.jpademojava.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService extends UserDetailsService {

    /**
     * Register new user
     *
     * @param user
     * @return
     */
    User register(UserRegisterRequest user);

    /**
     * Update user profile
     *
     * @param userId
     * @param user
     * @return
     */
    @Transactional
    User update(String userId, UserProfileUpdateRequest user) throws Exception;

    /**
     * Update user password
     *
     * @param userId
     * @param request
     * @return
     */
    @Transactional
    User updatePassword(String userId, UserPasswordUpdateRequest request) throws Exception;
}
