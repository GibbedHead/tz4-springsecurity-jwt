package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAdmin_whenUnauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin")
                )
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAdmin_whenRoleAdmin_shouldReturnOk() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getAdmin_whenRoleUSer_shouldReturnForbidden() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/admin")
                )
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
