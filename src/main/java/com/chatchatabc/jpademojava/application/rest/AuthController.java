package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.application.dto.user.UserLoginRequest;
import com.chatchatabc.jpademojava.application.dto.user.UserLoginResponse;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.JwtService;
import com.chatchatabc.jpademojava.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    /**
     * User Login
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(
            @RequestBody UserLoginRequest request
    ) {
        try {
            Optional<User> queriedUser = userRepository.findByUsername(request.getUsername());
            if (queriedUser.isEmpty()) {
                throw new Exception("User not found");
            }

            // Authenticate User
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Generate JWT
            String token = jwtService.generateToken(queriedUser.get());
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Access-Token", token);
            return ResponseEntity.ok().headers(headers)
                    .body(new UserLoginResponse(queriedUser.get(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UserLoginResponse(null, new ErrorContent("User Login Error", e.getMessage())));
        }
    }
}
