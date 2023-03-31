package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Register new user
     *
     * @param user
     * @return
     */
    @Override
    public User register(User user) {
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Update user profile
     *
     * @param userId
     * @param newUserInfo
     * @return
     */
    @Transactional
    @Override
    public User update(String userId, User newUserInfo) throws Exception {
        Optional<User> queriedUser = userRepository.findById(userId);
        if (queriedUser.isEmpty()) {
            throw new Exception("User not found");
        }

        // Apply update to fields if not null
        // TODO: Could be improved?
        if (newUserInfo.getEmail() != null) {
            queriedUser.get().setEmail(newUserInfo.getEmail());
        }
        if (newUserInfo.getUsername() != null) {
            queriedUser.get().setUsername(newUserInfo.getUsername());
        }
        return userRepository.save(queriedUser.get());
    }

    /**
     * Update user password
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Transactional
    @Override
    public User updatePassword(String userId, String oldPassword, String newPassword) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        // Compare old password with current password
        if (!passwordEncoder.matches(oldPassword, user.get().getPassword())) {
            throw new Exception("Old password is incorrect");
        }
        // Update password
        user.get().setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user.get());
    }

    /**
     * Load by username for Spring Security
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                user.get().isEnabled(),
                user.get().isAccountNonExpired(),
                user.get().isCredentialsNonExpired(),
                user.get().isAccountNonLocked(),
                user.get().getAuthorities()
        );
    }
}
