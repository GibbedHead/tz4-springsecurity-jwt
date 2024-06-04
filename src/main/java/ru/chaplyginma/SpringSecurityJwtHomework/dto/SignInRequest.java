package ru.chaplyginma.SpringSecurityJwtHomework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "SignIn request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInRequest {
    @Schema(description = "Username", example = "user_1")
    @Size(min = 1, max = 50)
    @NotBlank
    String username;

    @Schema(description = "Password", example = "mY_password")
    @NotBlank
    @Size(min = 1, max = 255)
    String password;
}
