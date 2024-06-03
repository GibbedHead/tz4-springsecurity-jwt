package ru.chaplyginma.SpringSecurityJwtHomework.mapper;

import org.mapstruct.Mapper;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User createUserRequestToUser(SignUpRequest signUpRequest);
}
