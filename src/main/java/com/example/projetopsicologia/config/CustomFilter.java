package com.example.projetopsicologia.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {

    public CustomFilter() {
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String secretHeader = request.getHeader("x-secret");
        if (secretHeader != null) {
            if (secretHeader.equals("secr3t")){
                Authentication authentication = new UsernamePasswordAuthenticationToken("Muito secreto", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request, response);
    }
}
