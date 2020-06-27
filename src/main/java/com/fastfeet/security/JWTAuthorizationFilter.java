package com.fastfeet.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fastfeet.Services.Exception.ObjectNotFound;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                response.setStatus(401);
                response.setContentType("application/json");
                response.getWriter().append("{\"timestamp\": ").append(String.valueOf(new Date().getTime())).append(", ").append("\"status\": 401, ").append("\"error\": \"Não autorizado\", ").append("\"message\": \"Token inválido\", ").append("\"path\": \"").append(request.getRequestURI()).append("\"}");
            }
        }
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            var userValid = jwtUtil.tokenValid(token);

            if (userValid != null) {
                UserDetails user = userDetailsService.loadUserByUsername(userValid);
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }
        } catch (TokenExpiredException e) {
            return null;
        }
        return null;

    }


}
