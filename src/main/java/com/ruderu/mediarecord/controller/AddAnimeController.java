package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.model.AnimeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AddAnimeController {

    static final String URL_EXAMPLE = "https://shikimori.one/api/animes?limit=10&search=";
    static final String URL_ID = "https://shikimori.one/api/animes/";

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
        RestTemplate restTemplate = new RestTemplate();
        AnimeModel[] models = restTemplate.getForObject(URL_EXAMPLE + anime.getNameEn(), AnimeModel[].class);
        List<AnimeModel> list = new ArrayList<>(Arrays.asList(models));
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
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(URL_ID + animeId);
        AnimeModel model = restTemplate.getForObject(URL_ID + animeId, AnimeModel.class);
        System.out.println(model);

        return "redirect:/anime";
    }
}
