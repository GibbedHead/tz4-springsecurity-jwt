package ru.chaplyginma.SpringSecurityJwtHomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
