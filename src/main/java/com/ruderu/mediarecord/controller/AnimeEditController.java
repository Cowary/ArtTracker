package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnimeEditController {

    @GetMapping("title/anime/editAnime")
    public String get(
            @RequestParam int animeId,
            Model model
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(animeId);
        model.addAttribute("animeModel", animeModel);
        return "animeEdit";
    }
}
