package com.lps.api.exceptions;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidInformationExceptionTest {

    @Test
    void testDefaultConstructor() {
        InvalidInformationException exception = new InvalidInformationException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Invalid information provided";
        InvalidInformationException exception = new InvalidInformationException(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Invalid information provided";
        Throwable cause = new RuntimeException("Root cause");
        InvalidInformationException exception = new InvalidInformationException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
