package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.application.dto.user.UserPasswordUpdateRequest;
import com.chatchatabc.jpademojava.application.dto.user.UserProfileUpdateRequest;
import com.chatchatabc.jpademojava.application.dto.user.UserRegisterRequest;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.UserService;
import org.modelmapper.ModelMapper;
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

    private ModelMapper mapper = new ModelMapper();

    /**
     * Register new user
     *
     * @param user
     * @return
     */
    @Override
    public User register(UserRegisterRequest user) {
        // Encrypt password
        User newUser = mapper.map(user, User.class);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    /**
     * Update user profile
     *
     * @param userId
     * @param user
     * @return
     */
    @Transactional
    @Override
    public User update(String userId, UserProfileUpdateRequest user) throws Exception {
        Optional<User> queriedUser = userRepository.findById(userId);
        if (queriedUser.isEmpty()) {
            throw new Exception("User not found");
        }

        // Apply update to fields if not null
        // TODO: Could be improved?
        if (user.getEmail() != null) {
            queriedUser.get().setEmail(user.getEmail());
        }
        if (user.getUsername() != null) {
            queriedUser.get().setUsername(user.getUsername());
        }
        return userRepository.save(queriedUser.get());
    }

    /**
     * Update user password
     *
     * @param userId
     * @param request
     * @return
     */
    @Transactional
    @Override
    public User updatePassword(String userId, UserPasswordUpdateRequest request) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        // Compare old password with current password
        if (!passwordEncoder.matches(request.getOldPassword(), user.get().getPassword())) {
            throw new Exception("Old password is incorrect");
        }
        // Update password
        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
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
