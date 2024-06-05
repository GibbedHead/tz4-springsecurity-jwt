package ru.chaplyginma.SpringSecurityJwtHomework.testdata;

import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignInRequest;

public class SignInTestData {
    public static SignInRequest getSignInRequest() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("user");
        signInRequest.setPassword("user");
        return signInRequest;
    }
}
