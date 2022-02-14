package com.ruderu.mediarecord.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void correctTest() throws Exception {
        this.mockMvc.perform(get("/title"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAnimeTest() throws Exception {
        this.mockMvc.perform(get("/title/anime/find"))
                .andExpect(status().isOk());
    }

    public void addAnimeTest() throws Exception {
        this.mockMvc.perform(get("/title/anime/addAnime"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
