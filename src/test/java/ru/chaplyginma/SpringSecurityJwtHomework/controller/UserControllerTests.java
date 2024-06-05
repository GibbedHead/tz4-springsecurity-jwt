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
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUser_whenUnauthenticated_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/user")
                )
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void getUser_whenAuthenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/user")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
