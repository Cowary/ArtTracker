package com.ruderu.arttracker.controller.media.manga;

import com.ruderu.arttracker.entity.manga.Manga;
import com.ruderu.arttracker.repo.MangaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("edit", true);

        return "media/manga/addManga";
    }

    @PostMapping("title/manga/edit")
    public String post(
            @ModelAttribute("manga") Manga manga
    ) {
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
