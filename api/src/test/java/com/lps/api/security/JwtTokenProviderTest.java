package com.lps.api.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

class JwtTokenProviderTest {

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    private String secretKey = "mySecretKey";
    private long expirationMinutes = 60;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtTokenProvider, "SECRET", secretKey);
        ReflectionTestUtils.setField(jwtTokenProvider, "EXPIRATION_MINUTES", expirationMinutes);
    }

    @Test
    void testGenerateToken_Success() {
        // Arrange
        String username = "testUser";
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);

        // Act
        String token = jwtTokenProvider.generateToken(authentication);

        // Assert
        assertNotNull(token);

        // Optional: Parse the token to check if it contains the expected values
        String parsedUsername = JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        assertNotNull(parsedUsername);
        assertEquals(username, parsedUsername);
    }

    @Test
    void testGenerateToken_Expiry() {
        // Arrange
        String username = "testUser";
        Instant now = Instant.now();
        Date expectedExpiryDate = Date.from(now.plusMillis(expirationMinutes * 60 * 1000L));
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);

        // Act
        String token = jwtTokenProvider.generateToken(authentication);

        // Assert
        assertNotNull(token);

        // Optional: Check expiry date in the token
        Date actualExpiryDate = JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
                .build()
                .verify(token)
                .getExpiresAt();

        assertNotNull(actualExpiryDate);
        assertEquals(expectedExpiryDate.getTime() / 1000, actualExpiryDate.getTime() / 1000);
    }
}
