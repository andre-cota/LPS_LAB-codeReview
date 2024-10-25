package com.lps.api.security;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lps.api.services.auth.AuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class JwtAuthorizationFilterTest {

    @Mock
    private AuthService authService;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    private String secret = "mySecretKey";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthorizationFilter.SECRET = secret;
    }

    @Test
    void testDoFilterInternalWithInvalidToken() throws IOException, ServletException {
        HttpServletRequest request = MockMvcRequestBuilders.get("/secured-endpoint")
                .header("Authorization", "Bearer invalidToken")
                .buildRequest(null);

        HttpServletResponse response = mock(HttpServletResponse.class);

        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternalWithIgnoredPath() throws IOException, ServletException {
        HttpServletRequest request = MockMvcRequestBuilders.get("/public/resource")
                .buildRequest(null);

        HttpServletResponse response = mock(HttpServletResponse.class);

        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}