package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;

    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        User user = userService.createUser(signUpRequest);
        String accessToken = jwtService.generateAccessToken(user.getUserName(), user.getId(), user.getRoles());

        return JwtAuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken("www")
                .build();
    }
}
