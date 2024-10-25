package com.lps.api.dtos.auth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testLoginRequestCreation() {
        String email = "test@example.com";
        String password = "securePassword";

        LoginRequest loginRequest = new LoginRequest(email, password);

        assertEquals(email, loginRequest.email());
        assertEquals(password, loginRequest.password());
    }

    @Test
    void testLoginRequestEquality() {
        LoginRequest request1 = new LoginRequest("test@example.com", "password123");
        LoginRequest request2 = new LoginRequest("test@example.com", "password123");

        assertEquals(request1, request2);
    }

    @Test
    void testLoginRequestHashCode() {
        LoginRequest request1 = new LoginRequest("test@example.com", "password123");
        LoginRequest request2 = new LoginRequest("test@example.com", "password123");

        assertEquals(request1.hashCode(), request2.hashCode());
    }
}