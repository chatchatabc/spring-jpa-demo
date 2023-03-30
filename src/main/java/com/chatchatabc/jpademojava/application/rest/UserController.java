package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.application.dto.user.UserProfileResponse;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

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
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new UserProfileResponse(null, new ErrorContent("User Profile Error", e.getMessage())));
        }
    }
}
