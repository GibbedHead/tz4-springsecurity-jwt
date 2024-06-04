package ru.chaplyginma.SpringSecurityJwtHomework.exception.model;

public class AccessTokenSignatureException extends RuntimeException {
    public AccessTokenSignatureException(String message) {
        super(message);
    }
}
