package com.ruderu.arttracker.controller.media.anime;

import com.ruderu.arttracker.dbCase.anime.AnimeStudioCrud;
import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.repo.AnimeRepo;
import com.ruderu.arttracker.util.DateFormat;
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
            @RequestParam long id,
            Model model
    ) {
        Anime anime = animeRepo.findById(id).orElseThrow();

        model.addAttribute("anime", anime);

        return "media/anime/editAnime";
    }

    @PostMapping("title/anime/edit")
    public String post(
            @RequestParam() long id,
            @RequestParam() String status,
            @RequestParam(required = false) String score,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam(required = false) String ongoingStart
    ) {
        Anime anime = animeRepo.findById(id).orElseThrow();
        anime.setStatus(status);
        if(!score.isBlank()) {
            anime.setScore(Integer.valueOf(score));
        }
        else {
            anime.setScore(null);
        }
        if(startDate != null) {
            anime.setStartDate(startDate);
        }
        if(endDate != null) {
            anime.setEndDate(endDate);
        }
        if(ongoingStart != null) anime.setOngoingStart(ongoingStart);
        System.out.println(anime);
        animeRepo.save(anime);
        return "redirect:../view/media";
    }

    @PostMapping("title/anime/delete")
    public String post(
            @RequestParam() long id
    ) {
        Anime anime = animeRepo.findById(id).orElseThrow();
        animeRepo.delete(anime);

        return "redirect:../view/media";
    }
}
