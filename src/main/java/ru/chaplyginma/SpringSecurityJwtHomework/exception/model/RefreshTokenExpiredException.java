package ru.chaplyginma.SpringSecurityJwtHomework.exception.model;

public class RefreshTokenExpiredException extends RuntimeException {
    public RefreshTokenExpiredException(String message) {
        super(message);
    }
}
