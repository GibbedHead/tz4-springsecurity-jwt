package ru.chaplyginma.SpringSecurityJwtHomework.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindByName_shouldReturnRole() {
        Role newRole = new Role("ROLE_TEST");

        Role savedRole = roleRepository.save(newRole);

        assertThat(savedRole.getName()).isEqualTo("ROLE_TEST");
    }
}
