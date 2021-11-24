package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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
            @RequestParam String name,
            @RequestParam Integer count,
            @RequestParam String status,
            @RequestParam Integer score,
            @RequestParam String author,
            @RequestParam String studio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ) {

        Anime anime = new Anime(name, count, status, score, author, studio, endDate);
        animeRepository.save(anime);
        return "redirect:/anime";

    }
}
