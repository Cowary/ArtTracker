package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddAnimeController {

    @GetMapping("/title/anime/add")
    public String get() {
        return "addAnim";
    }

    @PostMapping("/title/anime/add")
    public String post(
            @ModelAttribute("anime") Anime anime,
            Model model
    ) {
        System.out.println(anime.getNameEn());
        List<AnimeModel> list = ShikimoriApi.searchByName(anime.getNameEn());
        model.addAttribute("list", list);
        for (AnimeModel animeModel : list) {
            System.out.println(animeModel);
        }

        return "addAnim";

    }

    @PostMapping("/title/anime/saveAnime")
    public String post(
            @RequestParam int animeId,
            RedirectAttributes redirectAttributes
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(animeId);
        System.out.println(animeModel);
        redirectAttributes.addAttribute("animeId", animeId);

        return "redirect:./editAnime";
    }
}