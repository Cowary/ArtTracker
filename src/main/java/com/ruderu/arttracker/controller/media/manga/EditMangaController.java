package com.ruderu.arttracker.controller.media.manga;

import com.ruderu.arttracker.entity.manga.Manga;
import com.ruderu.arttracker.repo.MangaRep;
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
public class EditMangaController {

    @Autowired
    MangaRep mangaRep;

    @GetMapping("title/manga/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {

        Manga manga = mangaRep.findById(id).orElseThrow();

        model.addAttribute("manga", manga);

        return "media/manga/editManga";
    }

    @PostMapping("title/manga/edit")
    public String post(
            @RequestParam() long id,
            @RequestParam() String status,
            @RequestParam(required = false) String score,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam(required = false) String ongoingStart
    ) {
        Manga manga = mangaRep.findById(id).orElseThrow();
        manga.setStatus(status);
        if(!score.isBlank()) {
            manga.setScore(Integer.valueOf(score));
        }
        else {
            manga.setScore(null);
        }
        if(startDate != null) {
            manga.setStartDate(startDate);
        }
        if(endDate != null) {
            manga.setEndDate(endDate);
        }
        if(ongoingStart != null) manga.setOngoingStart(ongoingStart);
        System.out.println(manga);
        mangaRep.save(manga);
        return "redirect:../view/media";
    }

    @PostMapping("title/manga/delete")
    public String post(
            @RequestParam() long id
    ) {
        Manga manga = mangaRep.findById(id).orElseThrow();
        mangaRep.delete(manga);

        return "redirect:../view/media";
    }
}
