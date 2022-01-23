package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.repository.AnimeRepository;
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
    AnimeRepository animeRepository;

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
        System.out.println(anime.getNameEn());
        RestTemplate restTemplate = new RestTemplate();
        AnimeModel[] models = restTemplate.getForObject(URL_EXAMPLE, AnimeModel[].class);
        if (models != null) {
            model.addAttribute("AnimeModel", models[0]);
        }
        return "redirect:/anime";

    }
}
