package ru.chaplyginma.SpringSecurityJwtHomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
