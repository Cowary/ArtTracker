package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.dbCase.AnimeStudioCrud;
import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.model.StudioModel;
import com.ruderu.mediarecord.repository.AnimeRepository;
import com.ruderu.mediarecord.repository.AnimeStudioRep;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import com.ruderu.mediarecord.util.DateFormat;
import com.ruderu.mediarecord.util.DateUtil;
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
public class AddAnimeController {

    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    AnimeStudioRep animeStudioRep;
    @Autowired
    AnimeStudioCrud animeStudioCrud;

    @GetMapping("title/anime/addAnime")
    public String get(
            @RequestParam int animeId,
            Model model
    ) {
        File file = new File(FileUtil.path + "image" + ".jpeg");
        if(file.exists()) file.delete();
        AnimeModel animeModel = ShikimoriApi.findById(animeId);
        FileUtil.downloadFile("https://dere.shikimori.one" + animeModel.getImage().getOriginal(), "image");
        file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.exists(), "File");

        Anime anime = new Anime(animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), (long) animeModel.getId());
        List<StudioModel> studioModelList = List.of(animeModel.getStudios());
        System.out.println(anime);
        System.out.println(anime.getAiredOn());
        System.out.println(studioModelList);
        model.addAttribute("anime", anime);
        model.addAttribute("startDate", anime.getStartDate());
        model.addAttribute("endDate", anime.getEndDate());
        model.addAttribute("studioList", studioModelList);
        String url = "/resources/images/image.jpeg";
        model.addAttribute("image",url);
        System.out.println(DateFormat.HTML.format(anime.getAiredOn()));

        return "addAnim";
    }

    @PostMapping("title/anime/addAnime")
    public String post(
            @ModelAttribute("anime") Anime anime,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(Math.toIntExact(anime.getShikiId()));
        anime.setDuration(animeModel.getDuration());

        List<StudioModel> studioModelList = List.of(animeModel.getStudios());
        System.out.println(anime);
        System.out.println(startDate);
        System.out.println(endDate);
        if(startDate != null) {
            anime.setStartDate(startDate);
        } else {
            anime.setStartDate(DateUtil.def());
        }
        if(endDate != null) {
            anime.setEndDate(endDate);
        } else {
            anime.setEndDate(DateUtil.def());
        }

        System.out.println("!!!!");
        System.out.println(anime);
        animeRepository.save(anime);
        System.out.println(studioModelList);
        System.out.println("AFTE SAVE + " + anime);
        studioModelList.forEach(studioModel -> {
            animeStudioCrud.create(studioModel.getName(), anime.getId());
                }
        );
        File file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.delete(), "Rename file");

        return "addAnim";
    }
}
