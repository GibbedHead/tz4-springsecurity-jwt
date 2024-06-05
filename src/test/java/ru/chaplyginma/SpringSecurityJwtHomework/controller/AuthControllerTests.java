package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignInRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.TokenRefreshRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.service.AuthService;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.SignInRequestTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.SignUpRequestTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.TokenRefreshRequestTestData;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    public void signUp_whenValidSignUpRequest_shouldReturnCreated() throws Exception {
        SignUpRequest signUpRequest = SignUpRequestTestData.getSignUpRequest();
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        given(authService.signUp(signUpRequest))
                .willReturn(jwtAuthenticationResponse);

        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void signUp_whenInvalidSignUpRequest_shouldReturnBadRequest() throws Exception {
        SignUpRequest signUpRequest = SignUpRequestTestData.getSignUpRequest();
        signUpRequest.setUsername("");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        given(authService.signUp(signUpRequest))
                .willReturn(jwtAuthenticationResponse);

        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void signIn_whenValidSignInRequest_shouldReturnCreated() throws Exception {
        SignInRequest signInRequest = SignInRequestTestData.getSignInRequest();
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        given(authService.signIn(signInRequest))
                .willReturn(jwtAuthenticationResponse);

        String jsonRequest = objectMapper.writeValueAsString(signInRequest);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void signIn_whenInvalidSignInRequest_shouldReturnBadRequest() throws Exception {
        SignInRequest signInRequest = SignInRequestTestData.getSignInRequest();
        signInRequest.setUsername("");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        given(authService.signIn(signInRequest))
                .willReturn(jwtAuthenticationResponse);

        String jsonRequest = objectMapper.writeValueAsString(signInRequest);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void refresh_whenValidRefreshRequest_shouldReturnCreated() throws Exception {
        TokenRefreshRequest tokenRefreshRequest = TokenRefreshRequestTestData.getTokenRefreshRequest();
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        given(authService.refreshToken(tokenRefreshRequest))
                .willReturn(jwtAuthenticationResponse);

        String jsonRequest = objectMapper.writeValueAsString(tokenRefreshRequest);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/auth/refresh-token")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void refresh_whenInvalidRefreshRequest_shouldReturnBadRequest() throws Exception {
        TokenRefreshRequest tokenRefreshRequest = TokenRefreshRequestTestData.getTokenRefreshRequest();
        tokenRefreshRequest.setRefreshToken(null);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        given(authService.refreshToken(tokenRefreshRequest))
                .willReturn(jwtAuthenticationResponse);

        String jsonRequest = objectMapper.writeValueAsString(tokenRefreshRequest);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/auth/refresh-token")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
