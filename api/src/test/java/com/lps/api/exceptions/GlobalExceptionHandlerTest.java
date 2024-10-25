package com.lps.api.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.lps.api.dtos.ResponseDTO;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Test message");
        ResponseEntity<ResponseDTO> response = exceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("IllegalArgumentException", response.getBody().tittle());
        assertEquals("Test message", response.getBody().message());
    }

    @Test
    void testHandleEntityNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException("Test message");
        ResponseEntity<ResponseDTO> response = exceptionHandler.handleEntityNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("EntityNotFoundException", response.getBody().tittle());
        assertEquals("Test message", response.getBody().message());
    }

    @Test
    void testHandleNoSuchElementException() {
        NoSuchElementException exception = new NoSuchElementException("Test message");
        ResponseEntity<ResponseDTO> response = exceptionHandler.handleNoSuchElementException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("NoSuchElementException", response.getBody().tittle());
        assertEquals("Test message", response.getBody().message());
    }

    @Test
    void testHandleNoHandlerFoundException() {
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/test", null);
        ResponseEntity<ResponseDTO> response = exceptionHandler.handleNoHandlerFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("NoHandlerFoundException", response.getBody().tittle());
    }

    @Test
    void testRuntimeException() {
        RuntimeException exception = new RuntimeException("Test message");
        ResponseEntity<ResponseDTO> response = exceptionHandler.runtimeException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Exception", response.getBody().tittle());
        assertEquals("Test message", response.getBody().message());
    }

    @Test
    void testHandleMessagingException() {
        MessagingException exception = new MessagingException("Test message");
        ResponseEntity<ResponseDTO> response = exceptionHandler.handleMessagingException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("MessagingException", response.getBody().tittle());
        assertEquals("Test message", response.getBody().message());
    }

    @Test
    void testHandleUnsupportedEncodingException() {
        UnsupportedEncodingException exception = new UnsupportedEncodingException("Test message");
        ResponseEntity<ResponseDTO> response = exceptionHandler.handleUnsupportedEncodingException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("UnsupportedEncodingException", response.getBody().tittle());
        assertEquals("Test message", response.getBody().message());
    }
}