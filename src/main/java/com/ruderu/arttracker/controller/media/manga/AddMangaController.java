package com.ruderu.arttracker.controller.media.manga;

import com.ruderu.arttracker.dbCase.manga.MangaCrud;
import com.ruderu.arttracker.dbCase.manga.MangaPublisherCrud;
import com.ruderu.arttracker.dbCase.manga.MangaRoleCrud;
import com.ruderu.arttracker.entity.manga.Manga;
import com.ruderu.arttracker.rest.api.ShikimoriApi;
import com.ruderu.arttracker.rest.model.shiki.MangaModel;
import com.ruderu.arttracker.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddMangaController {

    @Autowired
    MangaCrud mangaCrud;
    @Autowired
    MangaPublisherCrud mangaPublisherCrud;
    @Autowired
    MangaRoleCrud mangaRoleCrud;

    @GetMapping("title/manga/add")
    public String get(
            @RequestParam(required = false) Integer mangaId,
            Model model
    ) {
        if(mangaId != null) {
            MangaModel mangaModel = ShikimoriApi.findMangaById(mangaId);

            Manga manga = new Manga(mangaModel.getName(), mangaModel.getRussian(), mangaModel.getVolumes(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()), (long) mangaModel.getId());
            model.addAttribute("manga", manga);
            String url = "https://dere.shikimori.one" + mangaModel.getImage().getOriginal();
            model.addAttribute("add", true);
            model.addAttribute("image",url);
        }

        return "media/manga/addManga";
    }

    @PostMapping("title/manga/add")
    public String post(
            @ModelAttribute("manga") Manga manga,
            RedirectAttributes redirectAttributes
    ) {
        mangaCrud.save(manga);

        if(manga.getShikiId() != null) {
            MangaModel mangaModel = ShikimoriApi.findMangaById(Math.toIntExact(manga.getShikiId()));
            mangaPublisherCrud.create(manga.getId(), List.of(mangaModel.getPublishers()));
            mangaRoleCrud.create(manga.getId(), List.of(mangaModel.getRoleModels()));
        }
        redirectAttributes.addAttribute("id", manga.getId());

        return "redirect:../manga/edit";
    }
}
