package ru.chaplyginma.SpringSecurityJwtHomework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignInRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.TokenRefreshRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.RefreshTokenTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.SignInTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.SignUpRequestTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.UserTestData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTests {
    @Mock
    UserService userService;
    @Mock
    JwtService jwtService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthService authService;

    @Test
    public void signUp_shouldReturnJwtAuthenticationResponse() {
        SignUpRequest signUpRequest = SignUpRequestTestData.getSignUpRequest();
        User user = UserTestData.getNewUser();
        String accessToken = "accessToken";
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();
        given(passwordEncoder.encode(anyString()))
                .willReturn("encoded pass");
        given(userService.createUser(signUpRequest))
                .willReturn(user);
        given(jwtService.generateAccessToken(user.getUsername(), user.getRoles()))
                .willReturn(accessToken);
        given(jwtService.getNewRefreshToken(user))
                .willReturn(refreshToken);

        JwtAuthenticationResponse jwtAuthenticationResponse = authService.signUp(signUpRequest);

        assertThat(jwtAuthenticationResponse.getAccessToken()).isEqualTo(accessToken);
        assertThat(jwtAuthenticationResponse.getRefreshToken()).isEqualTo(refreshToken.getTokenValue());
    }

    @Test
    public void signIn_shouldReturnJwtAuthenticationResponse() {
        SignInRequest signInRequest = SignInTestData.getSignInRequest();
        User user = UserTestData.getNewUser();
        String accessToken = "accessToken";
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();

        given(userService.findByUsername(signInRequest.getUsername()))
                .willReturn(Optional.of(user));
        given(jwtService.generateAccessToken(user.getUsername(), user.getRoles()))
                .willReturn(accessToken);
        given(jwtService.getNewRefreshToken(user))
                .willReturn(refreshToken);

        JwtAuthenticationResponse jwtAuthenticationResponse = authService.signIn(signInRequest);

        assertThat(jwtAuthenticationResponse.getAccessToken()).isEqualTo(accessToken);
        assertThat(jwtAuthenticationResponse.getRefreshToken()).isEqualTo(refreshToken.getTokenValue());
    }

    @Test
    public void refreshToken_shouldReturnJwtAuthenticationResponse() {
        String refreshTokenValue = "refreshToken";
        TokenRefreshRequest tokenRefreshRequest = new TokenRefreshRequest();
        tokenRefreshRequest.setRefreshToken(refreshTokenValue);
        SignInRequest signInRequest = SignInTestData.getSignInRequest();
        User user = UserTestData.getNewUser();
        String accessToken = "accessToken";
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();
        refreshToken.setUser(user);

        given(jwtService.getRefreshTokenByValue(refreshTokenValue))
                .willReturn(refreshToken);
        given(userService.findByUsername(signInRequest.getUsername()))
                .willReturn(Optional.of(user));
        given(jwtService.generateAccessToken(user.getUsername(), user.getRoles()))
                .willReturn(accessToken);

        JwtAuthenticationResponse jwtAuthenticationResponse = authService.refreshToken(tokenRefreshRequest);

        assertThat(jwtAuthenticationResponse.getAccessToken()).isEqualTo(accessToken);
        assertThat(jwtAuthenticationResponse.getRefreshToken()).isEqualTo(refreshToken.getTokenValue());
    }
}
