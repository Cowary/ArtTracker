package com.ruderu.arttracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MediaTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "kiki")
    public void mediaTest() throws Exception {
        this.mockMvc.perform(get("/title/view/media"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "kiki")
    public void playTest() throws Exception {
        this.mockMvc.perform(get("/title/view/play"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "kiki")
    public void readTest() throws Exception {
        this.mockMvc.perform(get("/title/view/read"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "kiki")
    public void watchTest() throws Exception {
        this.mockMvc.perform(get("/title/view/watch"))
                .andExpect(status().isOk());
    }
}
