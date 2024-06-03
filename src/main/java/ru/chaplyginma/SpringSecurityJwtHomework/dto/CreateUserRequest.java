package ru.chaplyginma.SpringSecurityJwtHomework.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotBlank
    String userName;
    @NotBlank
    String password;
    @NotBlank
    String email;
}
