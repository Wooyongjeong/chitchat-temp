package com.stock.chitchat.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.chitchat.user.dto.LoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper;

    public CustomAuthenticationFilter(ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/api/v1/users/login"));
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!isPost(request.getMethod())) {
            throw new HttpRequestMethodNotSupportedException("Wrong Http Method");
        }
        LoginDTO loginDTO = objectMapper.readValue(request.getReader(), LoginDTO.class);

        CustomAuthenticationToken token = new CustomAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        );
        return getAuthenticationManager().authenticate(token);
    }

    private boolean isPost(String method) {
        return "POST".equalsIgnoreCase(method);
    }
}
