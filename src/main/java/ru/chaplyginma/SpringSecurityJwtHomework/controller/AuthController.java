package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.JwtAuthenticationResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignInRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.TokenRefreshRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.AppErrorResponse;
import ru.chaplyginma.SpringSecurityJwtHomework.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication controller", description = "Endpoints for creating/logging users and refreshing access tokens")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "SignUp", description = "Signup new user",
            responses = {
                    @ApiResponse(description = "Object, containing access and refresh tokens",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtAuthenticationResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                 "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTc1Mjc1NTUsImV4cCI6MTcxNzUyODE1NSwic3ViIjoiTWFsdmluYSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdfQ.utQS9pSI-CBEx-s8P2O1YsVwe7ofjQLX-YAj8b3yZ9Y-817TXbzlnuUwdOqahKHX",
                                                 "refreshToken": "2bba917b-cc81-4058-b7a3-0fa5e4cf7e97"
                                             }""")
                            )),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppErrorResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "status": 400,
                                                "message": "Validation Error",
                                                "timestamp": "2024-06-04T22:40:46.7924577"
                                            }""")
                            )
                    )
            })
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.signUp(signUpRequest));
    }

    @Operation(summary = "SignIn", description = "Signin existed user",
            responses = {
                    @ApiResponse(description = "Object, containing access and refresh tokens",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtAuthenticationResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                 "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTc1Mjc1NTUsImV4cCI6MTcxNzUyODE1NSwic3ViIjoiTWFsdmluYSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdfQ.utQS9pSI-CBEx-s8P2O1YsVwe7ofjQLX-YAj8b3yZ9Y-817TXbzlnuUwdOqahKHX",
                                                 "refreshToken": "2bba917b-cc81-4058-b7a3-0fa5e4cf7e97"
                                             }""")
                            )),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppErrorResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "status": 400,
                                                "message": "Validation Error",
                                                "timestamp": "2024-06-04T22:40:46.7924577"
                                            }""")
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppErrorResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "status": 401,
                                                "message": "Authentication error: 'Bad credentials'",
                                                "timestamp": "2024-06-04T22:40:46.7924577"
                                            }""")
                            )
                    )
            })
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.signIn(signInRequest));
    }

    @Operation(summary = "Refresh access token", description = "Getting new access token by providing valid refresh token",
            responses = {
                    @ApiResponse(description = "Object, containing access and refresh tokens",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = JwtAuthenticationResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                 "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTc1Mjc1NTUsImV4cCI6MTcxNzUyODE1NSwic3ViIjoiTWFsdmluYSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdfQ.utQS9pSI-CBEx-s8P2O1YsVwe7ofjQLX-YAj8b3yZ9Y-817TXbzlnuUwdOqahKHX",
                                                 "refreshToken": "2bba917b-cc81-4058-b7a3-0fa5e4cf7e97"
                                             }""")
                            )),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppErrorResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "status": 400,
                                                "message": "Validation Error",
                                                "timestamp": "2024-06-04T22:40:46.7924577"
                                            }""")
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AppErrorResponse.class),
                                    examples = @ExampleObject(value = """
                                            {
                                                "status": 401,
                                                "message": "Refresh token error: 'Refresh token expired'",
                                                "timestamp": "2024-06-04T22:40:46.7924577"
                                            }""")
                            )
                    )
            })
    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.refreshToken(tokenRefreshRequest));
    }
}
