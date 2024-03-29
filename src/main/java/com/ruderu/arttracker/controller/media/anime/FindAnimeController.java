package com.ruderu.arttracker.controller.media.anime;

import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.rest.api.shiki.ShikimoriApi;
import com.ruderu.arttracker.rest.model.shiki.AnimeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FindAnimeController {

    @GetMapping("/title/anime/find")
    public String get() {
        return "media/anime/findAnime";
    }

    @PostMapping("/title/anime/find")
    public String post(
            @ModelAttribute("anime") Anime anime,
            Model model
    ) {
        List<AnimeModel> list = ShikimoriApi.animeApi().searchByName(anime.getOriginalTitle());
        model.addAttribute("list", list);

        return "media/anime/findAnime";

    }

    @PostMapping("/title/anime/saveAnime")
    public String post(
            @RequestParam int animeId,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("animeId", animeId);

        return "redirect:./add";
    }
}
