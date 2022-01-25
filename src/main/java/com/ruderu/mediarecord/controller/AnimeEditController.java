package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AnimeEditController {

    @GetMapping("title/anime/editAnime")
    public String get(
            @RequestParam int animeId,
            @RequestParam(defaultValue = "", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDateFormat,
            Model model
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(animeId);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Anime anime = null;
        try {
            anime = new Anime(animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), formatter2.parse(animeModel.getAired_on()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("anime", anime);

        if(anime.getEndDate() == null) {
            model.addAttribute("endDateFormat", "");
        } else {
            model.addAttribute("endDateFormat", formatter2.format(anime.getEndDate()));
        }
        return "animeEdit";
    }

    @PostMapping("title/anime/editAnime")
    public String post(
            @ModelAttribute("anime") Anime anime,
            @RequestParam(defaultValue = "" , required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDateFormat
    ) {
        System.out.println(anime);
        System.out.println(endDateFormat);

        return "animeEdit";
    }
}
