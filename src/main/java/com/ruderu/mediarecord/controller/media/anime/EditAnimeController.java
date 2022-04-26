package com.ruderu.mediarecord.controller.media.anime;

import com.ruderu.mediarecord.dbCase.anime.AnimeStudioCrud;
import com.ruderu.mediarecord.entity.anime.Anime;
import com.ruderu.mediarecord.repo.AnimeRepo;
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
    AnimeRepo animeRepo;
    @Autowired
    AnimeStudioCrud animeStudioCrud;

    @GetMapping("title/anime/edit")
    public String get(
            @RequestParam long animeId,
            Model model
    ) {
        Anime anime = animeRepo.findById(animeId).orElseThrow();

        model.addAttribute("anime", anime);

        return "media/anime/editAnime";
    }

    @PostMapping("title/anime/edit")
    public String post(
            @RequestParam() long animeId,
            @RequestParam() String status,
            @RequestParam() int score,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam() String ongoingStart
    ) {
        Anime anime = animeRepo.findById(animeId).orElseThrow();
        anime.setStatus(status);
        anime.setScore(score);
        if(startDate != null) {
            anime.setStartDate(startDate);
        }
        if(endDate != null) {
            anime.setEndDate(endDate);
        }
        if(ongoingStart != null) anime.setOngoingStart(ongoingStart);
        System.out.println(anime);
        animeRepo.save(anime);
        return "media/anime/editAnime";
    }

    @PostMapping("title/anime/delete")
    public String post(
            @RequestParam() long id
    ) {
        Anime anime = animeRepo.findById(id).orElseThrow();
        animeRepo.delete(anime);

        return "redirect:../anime";
    }
}
