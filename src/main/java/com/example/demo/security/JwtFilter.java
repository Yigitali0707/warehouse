package com.example.demo.security;


import com.example.demo.entity.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization!=null && authorization.startsWith("Bearer")){
            String token = authorization.substring(7);
            if (jwtUtil.isValid(token)){
                List<Role> authorities = jwtUtil.grantedAuthorities(token);
                System.out.println(authorities);
                var auth = new UsernamePasswordAuthenticationToken(
                jwtUtil.getUsername(token), null,
                        authorities
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
            filterChain.doFilter(request, response);


    }
}
