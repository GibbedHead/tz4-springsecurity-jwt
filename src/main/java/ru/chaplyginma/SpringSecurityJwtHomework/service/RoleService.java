package ru.chaplyginma.SpringSecurityJwtHomework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chaplyginma.SpringSecurityJwtHomework.exception.model.RoleNotFoundException;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.repository.RoleRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role '%s' not found".formatted(name)));
    }
}
