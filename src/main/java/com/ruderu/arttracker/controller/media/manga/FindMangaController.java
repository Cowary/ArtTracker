package com.ruderu.arttracker.controller.media.manga;

import com.ruderu.arttracker.entity.manga.Manga;
import com.ruderu.arttracker.rest.api.shiki.ShikimoriApi;
import com.ruderu.arttracker.rest.model.shiki.MangaModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FindMangaController {

    @GetMapping("/title/manga/find")
    public String get() {
        return "media/manga/findManga";
    }

    @PostMapping("/title/manga/find")
    public String post(
            @ModelAttribute("manga") Manga manga,
            Model model
    ) {
        List<MangaModel> list = ShikimoriApi.mangaApi().searchByName(manga.getOriginalTitle());
        model.addAttribute("list", list);

        return "media/manga/findManga";

    }

    @PostMapping("/title/manga/saveManga")
    public String post(
            @RequestParam int mangaId,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("mangaId", mangaId);

        return "redirect:./add";
    }
}
