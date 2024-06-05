package ru.chaplyginma.SpringSecurityJwtHomework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.InvalidUserException;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.UserRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.SignUpRequestTestData;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.UserTestData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    @Test
    public void givenUser_whenSaveUser_thenReturnSavedUser() {
        given(userRepository.save(any()))
                .willReturn(UserTestData.getSavedUser());

        User savedUser = userService.saveUser(UserTestData.getNewUser());

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isEqualTo(UserTestData.getSavedUser().getId());
    }

    @Test
    public void givenExistingUsernameSignUpRequest_whenCreateUser_thenThrowException() {
        given(userRepository.existsByUsername(any()))
                .willReturn(true);

        assertThatThrownBy(() -> userService.createUser(SignUpRequestTestData.getSignUpRequest()))
                .isInstanceOf(InvalidUserException.class);

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void givenExistingEmailSignUpRequest_whenCreateUser_thenThrowException() {
        given(userRepository.existsByEmail(any()))
                .willReturn(true);

        assertThatThrownBy(() -> userService.createUser(SignUpRequestTestData.getSignUpRequest()))
                .isInstanceOf(InvalidUserException.class);

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void givenNewUser_whenCreateUser_thenReturnSavedUser() {
        given(userRepository.save(any()))
                .willReturn(UserTestData.getSavedUser());

        given(roleService.getRoleByName("ROLE_USER"))
                .willReturn(new Role("ROLE_USER"));

        User createdUser = userService.createUser(SignUpRequestTestData.getSignUpRequest());

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getUsername()).isEqualTo("user");
        assertThat(createdUser.getEmail()).isEqualTo("user@email.com");
    }

    @Test
    public void givenNonExistingUsername_whenFindUserByUsername_thenReturnEmpty() {
        String username = "nonExistingUser";

        given(userRepository.findByUsername(username))
                .willReturn(Optional.empty());

        Optional<User> foundUser = userService.findByUsername(username);

        assertThat(foundUser).isNotPresent();
    }

    @Test
    public void givenExistingUsername_whenFindUserByUsername_thenReturnUser() {
        String username = UserTestData.getNewUser().getUsername();

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(UserTestData.getSavedUser()));

        Optional<User> foundUser = userService.findByUsername(username);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo(username);
    }

    @Test
    public void givenNonExistingUsername_whenLoadUserByUsername_thenThrowException() {
        String username = "nonExistingUser";

        given(userRepository.findByUsername(username))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    public void givenExistingEmail_whenLoadUserByEmail_thenReturnUserDetails() {
        User user = UserTestData.getSavedUser();


        given(userRepository.findByUsername(user.getUsername()))
                .willReturn(Optional.of(UserTestData.getSavedUser()));

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
    }
}