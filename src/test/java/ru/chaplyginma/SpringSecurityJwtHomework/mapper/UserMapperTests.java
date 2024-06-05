package ru.chaplyginma.SpringSecurityJwtHomework.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTests {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void givenNullSignUpRequest_whenMapToUser_thenReturnNull() {
        User user = userMapper.signUpRequestToUser(null);

        assertThat(user).isNull();
    }

    @Test
    public void givenSignUpRequest_whenMapToUser_thenReturnUser() {
        SignUpRequest signUpRequest = new SignUpRequest(
                "user",
                "pass",
                "email"
        );

        User user = userMapper.signUpRequestToUser(signUpRequest);

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(signUpRequest.getUsername());
        assertThat(user.getPassword()).isEqualTo(signUpRequest.getPassword());
        assertThat(user.getEmail()).isEqualTo(signUpRequest.getEmail());
    }
}
