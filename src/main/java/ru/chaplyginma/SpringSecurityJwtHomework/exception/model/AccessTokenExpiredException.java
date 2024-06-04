package ru.chaplyginma.SpringSecurityJwtHomework.exception.model;

public class AccessTokenExpiredException extends RuntimeException {
    public AccessTokenExpiredException(String message) {
        super(message);
    }
}
