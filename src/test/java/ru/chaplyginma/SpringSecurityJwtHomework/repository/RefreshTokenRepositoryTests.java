package ru.chaplyginma.SpringSecurityJwtHomework.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.RefreshTokenTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.UserTestData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class RefreshTokenRepositoryTests {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByTokenValue_shouldReturnRefreshToken() {
        User user = UserTestData.getNewUser();
        userRepository.save(user);
        RefreshToken refreshToken = RefreshTokenTestData.getRefreshToken();
        refreshToken.setUser(user);

        refreshTokenRepository.save(refreshToken);

        Optional<RefreshToken> savedRefreshToken = refreshTokenRepository.findByTokenValue(refreshToken.getTokenValue());

        assertThat(savedRefreshToken.isPresent()).isTrue();
        assertThat(savedRefreshToken.get().getUser()).isEqualTo(user);
    }
}
