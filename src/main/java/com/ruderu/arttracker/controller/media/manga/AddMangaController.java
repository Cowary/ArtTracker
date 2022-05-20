package com.ruderu.arttracker.controller.media.manga;

import com.ruderu.arttracker.dbCase.manga.MangaPublisherCrud;
import com.ruderu.arttracker.dbCase.manga.MangaRoleCrud;
import com.ruderu.arttracker.entity.manga.Manga;
import com.ruderu.arttracker.model.shiki.MangaModel;
import com.ruderu.arttracker.repo.MangaRep;
import com.ruderu.arttracker.rest.ShikimoriApi;
import com.ruderu.arttracker.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class AddMangaController {

    @Autowired
    MangaRep mangaRep;
    @Autowired
    MangaPublisherCrud mangaPublisherCrud;
    @Autowired
    MangaRoleCrud mangaRoleCrud;

    @GetMapping("title/manga/addManga")
    public String get(
            @RequestParam int mangaId,
            Model model
    ) {
        MangaModel mangaModel = ShikimoriApi.findMangaById(mangaId);

        Manga manga = new Manga(mangaModel.getName(), mangaModel.getRussian(), mangaModel.getVolumes(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()), (long) mangaModel.getId());
        model.addAttribute("manga", manga);
        model.addAttribute("startDate", manga.getStartDate());
        model.addAttribute("endDate", manga.getEndDate());
        model.addAttribute("ongoingStart", "no");
        String url = "https://dere.shikimori.one" + mangaModel.getImage().getOriginal();
        model.addAttribute("image",url);
        System.out.println(DateFormat.HTML.format(manga.getReleaseDate()));

        return "media/manga/addManga";
    }

    @PostMapping("title/manga/addManga")
    public String post(
            @ModelAttribute("manga") Manga manga,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam() String ongoingStart
    ) {
        MangaModel mangaModel = ShikimoriApi.findMangaById(Math.toIntExact(manga.getShikiId()));

        if(startDate != null) {
            manga.setStartDate(startDate);
        }
        if(endDate != null) {
            manga.setEndDate(endDate);
        }
        if(ongoingStart != null) manga.setOngoingStart(ongoingStart);

        mangaRep.save(manga);

        mangaPublisherCrud.create(manga.getId(), List.of(mangaModel.getPublishers()));
        mangaRoleCrud.create(manga.getId(), List.of(mangaModel.getRoleModels()));

//        File file = new File(FileUtil.path + "image" + ".jpeg");
//        Assert.isTrue(file.delete(), "Rename file");
        return "media/manga/addManga";
    }
}
