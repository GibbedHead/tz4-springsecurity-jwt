package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.InvalidUserException;
import ru.chaplyginma.SpringSecurityJwtHomework.mapper.UserMapper;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.UserRepository;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private static final String ROLE_USER = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(SignUpRequest signUpRequest) {
        User newUser = userMapper.signUpRequestToUser(signUpRequest);
        if (userRepository.existsByUserName(newUser.getUserName())) {
            throw new InvalidUserException("Username '%s' already exists".formatted(newUser.getUserName()));
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new InvalidUserException("Email '%s' already exists".formatted(newUser.getEmail()));
        }
        Role roleUser = roleService.getRoleByName(ROLE_USER);
        newUser.setRoles(Set.of(roleUser));
        return saveUser(newUser);
    }

}
