package ru.chaplyginma.SpringSecurityJwtHomework.testdata;

import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;

public class SignUpRequestTestData {
    public static SignUpRequest getSignUpRequest() {
        return new SignUpRequest(
                "user",
                "pass",
                "user@email.com"
        );
    }
}
