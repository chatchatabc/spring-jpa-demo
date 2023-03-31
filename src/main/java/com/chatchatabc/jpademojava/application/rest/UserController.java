package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.application.dto.user.UserPasswordUpdateRequest;
import com.chatchatabc.jpademojava.application.dto.user.UserProfileResponse;
import com.chatchatabc.jpademojava.application.dto.user.UserProfileUpdateRequest;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Get user profile
     *
     * @return
     */
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> profile() {
        try {
            // Get id from security context
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> user = userRepository.findById(principal.getId());
            if (user.isEmpty()) {
                throw new Exception("User not found");
            }
            return ResponseEntity.ok(new UserProfileResponse(user.get(), null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserProfileResponse(null, new ErrorContent("User Profile Error", e.getMessage())));
        }
    }

    /**
     * View profile of other user
     *
     * @param username
     * @return
     */
    @GetMapping("/profile/view/{username}")
    public ResponseEntity<UserProfileResponse> viewProfile(
            @PathVariable String username
    ) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isEmpty()) {
                throw new Exception("User not found");
            }
            return ResponseEntity.ok(new UserProfileResponse(user.get(), null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserProfileResponse(null, new ErrorContent("User Profile Error", e.getMessage())));
        }
    }

    /**
     * Update user profile
     *
     * @param request
     * @return
     */
    @PutMapping("/profile/update")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @RequestBody UserProfileUpdateRequest request
    ) {
        try {
            // Get id from security context
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User newUserInfo = modelMapper.map(request, User.class);
            User user = userService.update(principal.getId(), newUserInfo);
            return ResponseEntity.ok(new UserProfileResponse(user, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserProfileResponse(null, new ErrorContent("User Profile Error", e.getMessage())));
        }
    }

    /**
     * Update user password
     *
     * @param request
     * @return
     */
    @PutMapping("/profile/change-password")
    public ResponseEntity<UserProfileResponse> updatePassword(
            @RequestBody UserPasswordUpdateRequest request
    ) {
        try {
            // Get id from security context
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.updatePassword(principal.getId(), request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok(new UserProfileResponse(user, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new UserProfileResponse(null, new ErrorContent("User Profile Error", e.getMessage())));
        }
    }
}
