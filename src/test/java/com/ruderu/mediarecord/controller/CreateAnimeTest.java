package com.ruderu.mediarecord.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreateAnimeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void correctTest() throws Exception {
        this.mockMvc.perform(get("/title/anime/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Добавление аниме")))
        ;
    }

//    @Test
//    public void postTest() throws Exception {
//        this.mockMvc.perform(post())
//    }
}