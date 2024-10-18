package com.lps.api.exceptions;

public class InvalidInformationException extends RuntimeException {

    public InvalidInformationException() {

    }

    public InvalidInformationException(String message) {
        super(message);
    }

    public InvalidInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
