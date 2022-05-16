package com.ruderu.arttracker.controller;

import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.model.shiki.AnimeModel;
import com.ruderu.arttracker.repo.AnimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class CreateAnimeController {

    @Autowired
    AnimeRepo animeRepo;

    static final String URL_EXAMPLE = "https://shikimori.one/api/animes?season=2016,2015&limit=2";

    @GetMapping("/title/anime/create")
    public String get() {
        return "createAnim";
    }

    @PostMapping("/title/anime/create")
    public String post(
            @ModelAttribute("anime") Anime anime,
            Model model
    ) {
        RestTemplate restTemplate = new RestTemplate();
        AnimeModel[] models = restTemplate.getForObject(URL_EXAMPLE, AnimeModel[].class);
        if (models != null) {
            model.addAttribute("AnimeModel", models[0]);
        }
        return "redirect:/anime";

    }
}