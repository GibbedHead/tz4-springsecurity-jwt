package ru.chaplyginma.SpringSecurityJwtHomework.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.UserTestData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_shouldReturnUser() {
        User user = UserTestData.getNewUser();
        userRepository.save(user);

        Optional<User> userOptional = userRepository.findByUsername("user");

        assertThat(userOptional.isPresent()).isTrue();
        assertThat(userOptional.get().getUsername()).isEqualTo("user");
    }

    @Test
    public void existsByUsername_whenUsernameExists_shouldReturnTrue() {
        User user = UserTestData.getNewUser();
        userRepository.save(user);

        boolean exists = userRepository.existsByUsername(user.getUsername());

        assertThat(exists).isTrue();
    }

    @Test
    public void existsByEmail_whenUsernameExists_shouldReturnTrue() {
        User user = UserTestData.getNewUser();
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail(user.getEmail());

        assertThat(exists).isTrue();
    }


}
