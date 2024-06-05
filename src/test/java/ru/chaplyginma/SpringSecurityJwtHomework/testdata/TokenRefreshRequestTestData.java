package ru.chaplyginma.SpringSecurityJwtHomework.testdata;

import ru.chaplyginma.SpringSecurityJwtHomework.dto.TokenRefreshRequest;

public class TokenRefreshRequestTestData {
    public static TokenRefreshRequest getTokenRefreshRequest() {
        TokenRefreshRequest tokenRefreshRequest = new TokenRefreshRequest();
        tokenRefreshRequest.setRefreshToken("token");
        return tokenRefreshRequest;
    }
}
