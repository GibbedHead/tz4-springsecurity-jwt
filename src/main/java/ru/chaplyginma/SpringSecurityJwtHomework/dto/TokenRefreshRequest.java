package ru.chaplyginma.SpringSecurityJwtHomework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "Refresh tokens request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenRefreshRequest {
    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
    @NotNull
    String refreshToken;
}
