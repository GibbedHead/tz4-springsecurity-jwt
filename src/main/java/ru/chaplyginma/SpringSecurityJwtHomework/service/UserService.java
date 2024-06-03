package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.RoleNotFoundException;
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
    private static final String ROLE_USER = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);


    public User save(SignUpRequest signUpRequest) {
        User newUser = userMapper.createUserRequestToUser(signUpRequest);
        Role roleUser = roleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException("Role '%s' not found".formatted(ROLE_USER)));
        newUser.setRoles(Set.of(roleUser));
        return userRepository.save(newUser);
    }
}
