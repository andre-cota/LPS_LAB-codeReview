package com.lps.api.dtos.auth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void testNoArgsConstructor() {
        LoginResponse response = new LoginResponse();
        assertNull(response.getAccessToken());
        assertNull(response.getUserType());
        assertEquals("Bearer", response.getTokenType());
    }

    @Test
    void testAllArgsConstructor() {
        LoginResponse response = new LoginResponse("token123", "admin", "Bearer");
        assertEquals("token123", response.getAccessToken());
        assertEquals("admin", response.getUserType());
        assertEquals("Bearer", response.getTokenType());
    }

    @Test
    void testPartialConstructor() {
        LoginResponse response = new LoginResponse("token123", "admin");
        assertEquals("token123", response.getAccessToken());
        assertEquals("admin", response.getUserType());
        assertEquals("Bearer", response.getTokenType());
    }

    @Test
    void testBuilder() {
        LoginResponse response = LoginResponse.builder()
                .accessToken("token123")
                .userType("admin")
                .build();

        assertEquals("token123", response.getAccessToken());
        assertEquals("admin", response.getUserType());
        assertEquals("Bearer", response.getTokenType());
    }

    @Test
    void testSettersAndGetters() {
        LoginResponse response = new LoginResponse();
        response.setAccessToken("token123");
        response.setUserType("admin");
        response.setTokenType("Custom");

        assertEquals("token123", response.getAccessToken());
        assertEquals("admin", response.getUserType());
        assertEquals("Custom", response.getTokenType());
    }
}