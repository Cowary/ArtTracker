package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.dbCase.AnimeStudioCrud;
import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.repository.AnimeRepository;
import com.ruderu.mediarecord.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class EditAnimeController {

    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    AnimeStudioCrud animeStudioCrud;

    @GetMapping("title/anime/edit")
    public String get(
            @RequestParam long animeId,
            Model model
    ) {
        Anime anime = animeRepository.findById(animeId).orElseThrow();

        model.addAttribute("anime", anime);

        return "editAnime";
    }

    @PostMapping("title/anime/edit")
    public String post(
            @RequestParam() long animeId,
            @RequestParam() String status,
            @RequestParam() int score,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate
    ) {
        Anime anime = animeRepository.findById(animeId).orElseThrow();
        anime.setStatus(status);
        anime.setScore(score);
        if(startDate != null) {
            anime.setStartDate(startDate);
        }
        if(endDate != null) {
            anime.setEndDate(endDate);
        }
        System.out.println(anime);
        animeRepository.save(anime);
        return "editAnime";
    }
}
