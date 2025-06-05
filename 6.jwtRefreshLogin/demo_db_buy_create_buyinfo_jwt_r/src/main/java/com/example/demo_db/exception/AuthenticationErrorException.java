package com.example.demo_db.exception;

public class AuthenticationErrorException extends RuntimeException{
    public AuthenticationErrorException(String message) {
        super(message);
    }
}
