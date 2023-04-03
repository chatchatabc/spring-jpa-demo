package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.domain.enums.ROLE_NAMES;
import com.chatchatabc.jpademojava.domain.model.Role;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.RoleRepository;
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
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    private final ModelMapper modelMapper = new ModelMapper();


    /**
     * Register new user
     *
     * @param user
     * @return
     */
    @Override
    public User register(User user) {
        // Encrypt password
        User newUser = modelMapper.map(user, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // TODO: This can be moved to a separate service
        // Assign Role to user
        long count = userRepository.count();
        // Admin if first registered
        if (count == 0) {
            Optional<Role> adminRole = roleRepository.findRoleByName(ROLE_NAMES.ROLE_ADMIN.toString());
            if (adminRole.isEmpty()) {
                throw new RuntimeException("Admin role not found");
            }
            newUser.setRoles(Set.of(adminRole.get()));
        } else {
            Optional<Role> userRole = roleRepository.findRoleByName(ROLE_NAMES.ROLE_USER.toString());
            if (userRole.isEmpty()) {
                throw new RuntimeException("User role not found");
            }
            newUser.setRoles(Set.of(userRole.get()));
        }
        return userRepository.save(newUser);
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
