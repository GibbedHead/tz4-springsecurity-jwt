package ru.chaplyginma.SpringSecurityJwtHomework.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.*;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<Object> handleRefreshTokenNotFoundException(RefreshTokenNotFoundException ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Refresh token error: '%s'".formatted(ex.getMessage())
        );

        return new ResponseEntity<>(appErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Role error: '%s'".formatted(ex.getMessage())
        );

        return new ResponseEntity<>(appErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<Object> handleInvalidUserException(InvalidUserException ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "User error: '%s'".formatted(ex.getMessage())
        );

        return new ResponseEntity<>(appErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<Object> handleRefreshTokenExpiredException(RefreshTokenExpiredException ex) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Refresh token error: '%s'".formatted(ex.getMessage())
        );

        return new ResponseEntity<>(appErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        AppErrorResponse appErrorResponse = new AppErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Method argument error: '%s'".formatted(ex.getMessage())
        );

        return new ResponseEntity<>(appErrorResponse, HttpStatus.BAD_REQUEST);
    }


}
