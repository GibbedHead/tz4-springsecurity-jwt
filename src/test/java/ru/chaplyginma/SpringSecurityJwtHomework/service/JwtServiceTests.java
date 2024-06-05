package ru.chaplyginma.SpringSecurityJwtHomework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.RefreshTokenExpiredException;
import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.RefreshTokenTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.UserTestData;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTests {
    @InjectMocks
    private JwtService jwtService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtService, "jwtSecret", "faadd63584c84e672ff5824b4f85226faadd63584c84e672ff5824b4f85226");
        ReflectionTestUtils.setField(jwtService, "accessTokenExpiration", Duration.ofMinutes(10));
        ReflectionTestUtils.setField(jwtService, "refreshTokenExpiration", Duration.ofMinutes(30));
    }

    @Test
    public void generateAccessToken_shouldGenerateAccessToken() {
        String username = "user";
        Set<Role> roles = Set.of(new Role("ROLE_USER"));

        String accessToken = jwtService.generateAccessToken(username, roles);

        assertThat(accessToken).isNotBlank();
    }

    @Test
    public void getNewRefreshToken_shouldReturnRefreshToken() {
        User user = UserTestData.getSavedUser();
        RefreshToken generatedRefreshToken = jwtService.generateRefreshToken(user);

        given(refreshTokenService.save(any()))
                .willReturn(generatedRefreshToken);

        RefreshToken savedRefreshToken = jwtService.getNewRefreshToken(user);

        assertThat(savedRefreshToken).isNotNull();
        verify(refreshTokenService, times(1)).save(any(RefreshToken.class));
    }

    @Test
    public void generateRefreshToken_shouldGenerateRefreshToken() {
        User user = UserTestData.getSavedUser();
        RefreshToken refreshToken = jwtService.generateRefreshToken(user);

        assertThat(refreshToken).isNotNull();
        assertThat(refreshToken.getUser()).isEqualTo(user);
    }

    @Test
    public void getRefreshTokenByValue_shouldReturnRefreshToken() {
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();

        given(refreshTokenService.findByValue(anyString()))
                .willReturn(refreshToken);

        RefreshToken foundToken = jwtService.getRefreshTokenByValue("value");

        assertThat(foundToken).isNotNull();
        assertThat(foundToken.getUser()).isEqualTo(refreshToken.getUser());
    }

    @Test
    public void validateRefreshToken_whenRefreshTokenIsValid_shouldNotDeleteRefreshToken_andShouldNotThrowException() {
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();
        refreshToken.setExpireAt(LocalDateTime.now().plusHours(1));

        assertThatCode(() -> jwtService.validateRefreshToken(refreshToken))
                .doesNotThrowAnyException();

        verify(refreshTokenService, never()).delete(any());
    }

    @Test
    public void validateRefreshToken_whenRefreshTokenInvalid_shouldDeleteRefreshToken_andShouldThrowException() {
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();
        refreshToken.setExpireAt(LocalDateTime.now().minusHours(1));

        assertThatThrownBy(() -> jwtService.validateRefreshToken(refreshToken))
                .isInstanceOf(RefreshTokenExpiredException.class)
                .hasMessageContaining("Refresh token expired");

        verify(refreshTokenService, times(1)).delete(any());
    }
}
