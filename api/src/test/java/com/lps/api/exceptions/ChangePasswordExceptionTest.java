package com.lps.api.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordExceptionTest {

    @Test
    void testDefaultConstructor() {
        ChangePasswordException exception = new ChangePasswordException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Password change failed";
        ChangePasswordException exception = new ChangePasswordException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Password change failed";
        Throwable cause = new RuntimeException("Underlying cause");
        ChangePasswordException exception = new ChangePasswordException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}