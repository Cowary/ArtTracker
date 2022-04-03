package com.ruderu.mediarecord.controller.media.manga;

import com.ruderu.mediarecord.dbCase.MangaPublisherCrud;
import com.ruderu.mediarecord.dbCase.MangaRoleCrud;
import com.ruderu.mediarecord.entity.Manga;
import com.ruderu.mediarecord.model.shiki.MangaModel;
import com.ruderu.mediarecord.repo.MangaRep;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import com.ruderu.mediarecord.util.DateFormat;
import com.ruderu.mediarecord.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
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
        File file = new File(FileUtil.path + "image" + ".jpeg");
        if(file.exists()) file.delete();
        MangaModel mangaModel = ShikimoriApi.findMangaById(mangaId);
        FileUtil.downloadFile("https://dere.shikimori.one" + mangaModel.getImage().getOriginal(), "image");
        file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.exists(), "File");

        Manga manga = new Manga(mangaModel.getName(), mangaModel.getRussian(), mangaModel.getVolumes(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()), (long) mangaModel.getId());
        //List<StudioModel> studioModelList = List.of(mangaModel.getStudios());
        model.addAttribute("manga", manga);
        model.addAttribute("startDate", manga.getStartDate());
        model.addAttribute("endDate", manga.getEndDate());
        //model.addAttribute("studioList", studioModelList);
        model.addAttribute("ongoingStart", "no");
        String url = "/resources/images/image.jpeg";
        model.addAttribute("image",url);
        System.out.println(DateFormat.HTML.format(manga.getAiredOn()));

        return "media/manga/addManga";
    }

    @PostMapping("title/manga/addManga")
    public String post(
            @ModelAttribute("manga") Manga manga,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam(required = false) String comment,
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
        if(comment != null) manga.setComment(comment);

        mangaRep.save(manga);

        mangaPublisherCrud.create(manga.getId(), List.of(mangaModel.getPublishers()));
        mangaRoleCrud.create(manga.getId(), List.of(mangaModel.getRoleModels()));

        File file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.delete(), "Rename file");
        return "media/manga/addManga";
    }
}
