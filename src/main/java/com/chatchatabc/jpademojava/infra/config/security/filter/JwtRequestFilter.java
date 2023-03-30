package com.chatchatabc.jpademojava.infra.config.security.filter;

import com.chatchatabc.jpademojava.application.rest.jwt.JwtService;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

    /**
     * Filter the request and validate the token
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get authorization header and validate
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            logRequest(request, response);
            return;
        }

        String token = header.substring(7);
        String userId = jwtService.validateTokenAndGetId(token);

        // Get the user from the database
        Optional<User> user = userRepository.findById(userId);

        // If the user is not found
        if (user.isEmpty()) {
            filterChain.doFilter(request, response);
            logRequest(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.get(),
                null,
                user.get().getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue flow with authenticated user
        filterChain.doFilter(request, response);
        logRequest(request, response, userId);
    }

    /**
     * Log the request
     *
     * @param request
     * @param response
     */
    private void logRequest(HttpServletRequest request, HttpServletResponse response) {
        // Log path of the request
        log.info("Request path: " + request.getMethod() + " " + request.getRequestURL() + " from " + request.getRemoteAddr() + " with code " + response.getStatus());
    }

    /**
     * Log the request with user id
     *
     * @param request
     * @param response
     */
    private void logRequest(HttpServletRequest request, HttpServletResponse response, String userId) {
        // Log path of the request
        log.info("Request path: " + request.getMethod() + " " + request.getRequestURL() + " User ID " + userId + " from " + request.getRemoteAddr() + " with code " + response.getStatus());
    }
}
