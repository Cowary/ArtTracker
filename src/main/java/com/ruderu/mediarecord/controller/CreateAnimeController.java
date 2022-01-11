package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateAnimeController {

    @Autowired
    AnimeRepository animeRepository;

    @GetMapping("/title/anime/add")
    public String get() {
        return "addAnim";
    }

    @PostMapping("/title/anime/add")
    public String post(
            @ModelAttribute("anime") Anime anime
    ) {
        System.out.println(anime.getEndDate());
        animeRepository.save(anime);
        return "redirect:/anime";

    }
}
