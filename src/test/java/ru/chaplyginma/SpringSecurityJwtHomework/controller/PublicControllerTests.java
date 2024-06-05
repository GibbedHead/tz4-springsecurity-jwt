package ru.chaplyginma.SpringSecurityJwtHomework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PublicControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPublic_whenUnauthenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/public")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getPublic_whenAuthenticated_shouldReturnOk() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/public")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
