package ru.chaplyginma.SpringSecurityJwtHomework.testdata;

import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;
import ru.chaplyginma.SpringSecurityJwtHomework.model.User;

import java.util.Set;

public class UserTestData {

    public static User getNewUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("pass");
        user.setEmail("user@email.com");
        return user;
    }

    public static User getSavedUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user");
        user.setPassword("pass");
        user.setEmail("user@email.com");
        user.setRoles(Set.of(new Role("ROLE_USER")));
        return user;
    }
}
