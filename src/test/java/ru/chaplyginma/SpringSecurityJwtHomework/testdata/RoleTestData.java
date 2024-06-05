package ru.chaplyginma.SpringSecurityJwtHomework.testdata;

import ru.chaplyginma.SpringSecurityJwtHomework.model.Role;

public class RoleTestData {
    public static Role getRole() {
        return new Role("ROLE_USER");
    }
}
