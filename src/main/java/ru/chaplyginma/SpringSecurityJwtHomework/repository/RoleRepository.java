package ru.chaplyginma.SpringSecurityJwtHomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
