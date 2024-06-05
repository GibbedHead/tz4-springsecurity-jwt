package ru.chaplyginma.SpringSecurityJwtHomework.testdata;

import ru.chaplyginma.SpringSecurityJwtHomework.model.RefreshToken;

import java.time.LocalDateTime;

public class RefreshTokenTestData {
    public static RefreshToken getRefreshToken() {
        return new RefreshToken(
                1L,
                UserTestData.getNewUser(),
                "value",
                LocalDateTime.now()
        );
    }
}
