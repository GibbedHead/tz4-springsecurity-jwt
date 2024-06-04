package ru.chaplyginma.SpringSecurityJwtHomework.exception.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppErrorResponse {
    int status;
    String message;
    LocalDateTime timestamp;

    public AppErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
