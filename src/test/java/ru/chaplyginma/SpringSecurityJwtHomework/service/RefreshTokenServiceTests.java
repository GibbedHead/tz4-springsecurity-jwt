package ru.chaplyginma.SpringSecurityJwtHomework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.RefreshTokenNotFoundException;
import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.RefreshTokenRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.RefreshTokenTestData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RefreshTokenServiceTests {
    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private RefreshTokenService refreshTokenService;

    @Test
    public void findByValue_whenTokenExists_thenReturnRefreshToken() {
        given(refreshTokenRepository.findByValue(anyString()))
                .willReturn(Optional.of(RefreshTokenTestData.getRefreshToken()));

        RefreshToken foundRefreshToken = refreshTokenService.findByValue("value");

        assertThat(foundRefreshToken).isNotNull();

        assertThat(foundRefreshToken.getValue()).isEqualTo(RefreshTokenTestData.getRefreshToken().getValue());
    }

    @Test
    public void findByValue_whenTokenNotExists_thenThrowException() {
        given(refreshTokenRepository.findByValue(anyString()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> refreshTokenService.findByValue("value"))
                .isInstanceOf(RefreshTokenNotFoundException.class)
                .hasMessageContaining("Refresh token 'value' not found");
    }

    @Test
    public void save_whenToken_shouldSaveRefreshToken() {
        RefreshToken expectedToken = RefreshTokenTestData.getRefreshToken();

        given(refreshTokenRepository.save(expectedToken))
                .willReturn(expectedToken);

        RefreshToken savedRefreshToken = refreshTokenService.save(expectedToken);

        assertThat(savedRefreshToken.getValue()).isEqualTo(expectedToken.getValue());
        verify(refreshTokenRepository, times(1)).save(expectedToken);
    }

    @Test
    public void delete_whenInvoked_shouldDeleteRefreshToken() {
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();

        refreshTokenService.delete(refreshToken);

        verify(refreshTokenRepository, times(1)).delete(refreshToken);
    }
}
