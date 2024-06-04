package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chaplyginma.SpringSecurityJwtHomework.dto.SignUpRequest;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.InvalidUserException;
import ru.chaplyginma.SpringSecurityJwtHomework.mapper.UserMapper;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private static final String ROLE_USER = "ROLE_USER";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(SignUpRequest signUpRequest) {
        User newUser = userMapper.signUpRequestToUser(signUpRequest);
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new InvalidUserException("Username '%s' already exists".formatted(newUser.getUsername()));
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new InvalidUserException("Email '%s' already exists".formatted(newUser.getEmail()));
        }
        Role roleUser = roleService.getRoleByName(ROLE_USER);
        newUser.setRoles(Set.of(roleUser));
        return saveUser(newUser);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User '%s' not found".formatted(username))
        );
        return mapUserToUserDetails(user);
    }

    private UserDetails mapUserToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}
