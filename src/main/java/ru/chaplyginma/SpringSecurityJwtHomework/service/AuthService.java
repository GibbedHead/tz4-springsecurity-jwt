package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignInRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.TokenRefreshRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        User user = userService.createUser(signUpRequest);
        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRoles());
        RefreshToken refreshToken = jwtService.getNewRefreshToken(user);

        return JwtAuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getTokenValue())
                .build();
    }

    @Transactional
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),
                signInRequest.getPassword()
        ));
        User user = userService.findByUsername(signInRequest.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User '%s' not found".formatted(signInRequest.getUsername()))
        );

        String accessToken = jwtService.generateAccessToken(
                user.getUsername(),
                user.getRoles()
        );
        RefreshToken refreshToken = jwtService.getNewRefreshToken(user);

        return JwtAuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getTokenValue())
                .build();
    }

    @Transactional
    public JwtAuthenticationResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        RefreshToken refreshToken = jwtService.getRefreshTokenByValue(tokenRefreshRequest.getRefreshToken());
        jwtService.validateRefreshToken(refreshToken);

        User user = userService.findByUsername(refreshToken.getUser().getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User '%s' not found".formatted(refreshToken.getUser().getUsername()))
        );

        String accessToken = jwtService.generateAccessToken(
                user.getUsername(),
                user.getRoles()
        );

        return JwtAuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getTokenValue())
                .build();
    }
}
