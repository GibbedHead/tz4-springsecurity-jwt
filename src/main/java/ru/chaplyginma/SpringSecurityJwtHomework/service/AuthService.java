package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignInRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        User user = userService.createUser(signUpRequest);
        String accessToken = jwtService.generateAccessToken(user.getUserName(), user.getId(), user.getRoles());
        RefreshToken refreshToken = jwtService.getNewRefreshToken(user);

        return JwtAuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getValue())
                .build();
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        return null;
    }
}
