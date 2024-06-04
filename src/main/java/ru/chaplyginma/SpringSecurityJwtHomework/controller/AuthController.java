package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignInRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return null;
    }
}
