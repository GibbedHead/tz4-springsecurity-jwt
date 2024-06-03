package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }
}
