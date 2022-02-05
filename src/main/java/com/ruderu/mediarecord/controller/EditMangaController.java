package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Manga;
import com.ruderu.mediarecord.repository.MangaRep;
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
public class EditMangaController {

    @Autowired
    MangaRep mangaRep;

    @GetMapping("title/manga/edit")
    public String get(
            @RequestParam long mangaId,
            Model model
    ) {

        Manga manga = mangaRep.findById(mangaId).orElseThrow();

        model.addAttribute("manga", manga);

        return "editManga";
    }

    @PostMapping("title/manga/edit")
    public String post(
            @RequestParam() long animeId,
            @RequestParam() String status,
            @RequestParam() int score,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam() String ongoingStart
    ) {
        Manga manga = mangaRep.findById(animeId).orElseThrow();
        manga.setStatus(status);
        manga.setScore(score);
        if(startDate != null) {
            manga.setStartDate(startDate);
        }
        if(endDate != null) {
            manga.setEndDate(endDate);
        }
        if(ongoingStart != null) manga.setOngoingStart(ongoingStart);
        System.out.println(manga);
        mangaRep.save(manga);
        return "editAnime";
    }

    @PostMapping("title/manga/delete")
    public String post(
            @RequestParam() long id
    ) {
        Manga manga = mangaRep.findById(id).orElseThrow();
        mangaRep.delete(manga);

        return "redirect:../anime";
    }
}
