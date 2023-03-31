package com.chatchatabc.jpademojava.domain.service;

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
    User register(User user);

    /**
     * Update user profile
     *
     * @param userId
     * @param newUserInfo
     * @return
     */
    @Transactional
    User update(String userId, User newUserInfo) throws Exception;

    /**
     * Update user password
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Transactional
    User updatePassword(String userId, String oldPassword, String newPassword) throws Exception;
}
