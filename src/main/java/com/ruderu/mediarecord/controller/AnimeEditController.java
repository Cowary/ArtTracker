package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import com.ruderu.mediarecord.util.DateFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class AnimeEditController {

    @GetMapping("title/anime/editAnime")
    public String get(
            @RequestParam int animeId,
            Model model
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(animeId);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

        Anime anime = null;
        try {
            anime = new Anime(animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(),
                    formatter2.parse(animeModel.getAired_on()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(anime);
        System.out.println(anime.getAiredOn());
        model.addAttribute("anime", anime);

        System.out.println(DateFormat.HTML.format(anime.getAiredOn()));
        return "animeEdit";
    }

    @PostMapping("title/anime/editAnime")
    public String post(
            @ModelAttribute("anime") Anime anime
    ) {
        System.out.println(anime);

        return "animeEdit";
    }
}
