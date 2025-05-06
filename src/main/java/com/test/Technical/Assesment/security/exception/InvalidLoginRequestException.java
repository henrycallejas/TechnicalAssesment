package com.test.Technical.Assesment.security.exception;

public class InvalidLoginRequestException extends RuntimeException{
    public InvalidLoginRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
