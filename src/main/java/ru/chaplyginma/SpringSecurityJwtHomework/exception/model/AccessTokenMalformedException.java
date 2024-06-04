package ru.chaplyginma.SpringSecurityJwtHomework.exception.model;

public class AccessTokenMalformedException extends RuntimeException {
    public AccessTokenMalformedException(String message) {
        super(message);
    }
}
