package com.chatchatabc.jpademojava.infra.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    // TODO: Autowire jwtService
    // TODO: Autowire userRepository

    /**
     * Filter the request and validate the token
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

        // TODO: Implement logic

        // TODO: Continue flow with authenticated user
        filterChain.doFilter(request, response);
    }
}
