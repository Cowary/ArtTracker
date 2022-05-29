package com.ruderu.arttracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TitleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void correctTest() throws Exception {
        this.mockMvc.perform(get("/title"))
                .andExpect(status().isOk());
    }

    @Test
    public void addAnimeTest() throws Exception {
        this.mockMvc.perform(get("/title/anime/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAnimeTest() throws Exception {
        this.mockMvc.perform(get("/title/anime/find"))
                .andExpect(status().isOk());
    }

    @Test
    public void addMangaTest() throws Exception {
        this.mockMvc.perform(get("/title/manga/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void findMangaTest() throws Exception {
        this.mockMvc.perform(get("/title/manga/find"))
                .andExpect(status().isOk());
    }

    @Test
    public void addRanobeTest() throws Exception {
        this.mockMvc.perform(get("/title/ranobe/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void findRanobeTest() throws Exception {
        this.mockMvc.perform(get("/title/ranobe/find"))
                .andExpect(status().isOk());
    }

    @Test
    public void addMovieTest() throws Exception {
        this.mockMvc.perform(get("/title/movie/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void findMovieTest() throws Exception {
        this.mockMvc.perform(get("/title/movie/find"))
                .andExpect(status().isOk());
    }

    @Test
    public void addTvTest() throws Exception {
        this.mockMvc.perform(get("/title/tv/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void findTvTest() throws Exception {
        this.mockMvc.perform(get("/title/tv/find"))
                .andExpect(status().isOk());
    }

    @Test
    public void addBookTest() throws Exception {
        this.mockMvc.perform(get("/title/book/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void addGameTest() throws Exception {
        this.mockMvc.perform(get("/title/game/add"))
                .andExpect(status().isOk());
    }
}
