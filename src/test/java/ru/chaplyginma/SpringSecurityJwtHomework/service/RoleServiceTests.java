package ru.chaplyginma.SpringSecurityJwtHomework.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.RoleNotFoundException;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.RoleRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.testdata.RoleTestData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTests {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void getRoleByName_whenRoleExists_shouldReturnRole() {
        given(roleRepository.findByName(any()))
                .willReturn(Optional.of(RoleTestData.getRole()));

        Role foundRole = roleService.getRoleByName(RoleTestData.getRole().getName());

        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getName()).isEqualTo(RoleTestData.getRole().getName());
    }

    @Test
    public void getRoleByName_whenRoleDoesNotExist_shouldThrowException() {
        String roleName = "nonexistent";

        given(roleRepository.findByName(anyString()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> roleService.getRoleByName(roleName))
                .isInstanceOf(RoleNotFoundException.class)
                .hasMessageContaining("Role '" + roleName + "' not found");
    }
}
