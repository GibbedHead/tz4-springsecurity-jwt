package ru.chaplyginma.SpringSecurityJwtHomework.exception.model;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
