package ru.chaplyginma.SpringSecurityJwtHomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
