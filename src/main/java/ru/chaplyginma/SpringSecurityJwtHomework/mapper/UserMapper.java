package ru.chaplyginma.SpringSecurityJwtHomework.mapper;

import org.mapstruct.Mapper;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.CreateUserRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User createUserRequestToUser(CreateUserRequest createUserRequest);
}
