package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.CreateUserRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.mapper.UserMapper;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.RoleRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.UserRepository;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public User save(CreateUserRequest createUserRequest) {
        User newUser = userMapper.createUserRequestToUser(createUserRequest);
        Role roleUser = roleRepository.findByName("ROLE_USER");
        newUser.setRoles(Set.of(roleUser));
        return userRepository.save(newUser);
    }
}
