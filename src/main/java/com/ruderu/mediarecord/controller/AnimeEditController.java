package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.entity.AnimeStudio;
import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.model.Studio;
import com.ruderu.mediarecord.repository.AnimeRepository;
import com.ruderu.mediarecord.repository.AnimeStudioRep;
import com.ruderu.mediarecord.rest.ShikimoriApi;
import com.ruderu.mediarecord.util.DateFormat;
import com.ruderu.mediarecord.util.DateUtil;
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
public class AnimeEditController {

    @Autowired
    AnimeRepository animeRepository;
    @Autowired
    AnimeStudioRep animeStudioRep;

    @GetMapping("title/anime/editAnime")
    public String get(
            @RequestParam int animeId,
            Model model
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(animeId);

        Anime anime = new Anime(animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), (long) animeModel.getId());
        List<Studio> studioList = List.of(animeModel.getStudios());
        System.out.println(anime);
        System.out.println(anime.getAiredOn());
        System.out.println(studioList);
        model.addAttribute("anime", anime);
        model.addAttribute("startDate", DateFormat.ddMMyyyy.format(DateUtil.now()));
        model.addAttribute("endDate", anime.getEndDate());
        model.addAttribute("studioList", studioList);
        System.out.println(DateFormat.HTML.format(anime.getAiredOn()));

        return "animeEdit";
    }

    @PostMapping("title/anime/editAnime")
    public String post(
            @ModelAttribute("anime") Anime anime,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(Math.toIntExact(anime.getShikiId()));
        List<Studio> studioList = List.of(animeModel.getStudios());
        System.out.println(anime);
        System.out.println(startDate);
        System.out.println(endDate);
        if(startDate != null) anime.setStartDate(startDate);
        if(endDate != null) anime.setEndDate(endDate);
        System.out.println("!!!!");
        System.out.println(anime);
        animeRepository.save(anime);
        System.out.println(studioList);
        System.out.println("AFTE SAVE + " + anime);
        studioList.forEach( studio -> {
            AnimeStudio animeStudio = new AnimeStudio(studio.getName(), studio.getFiltered_name(), studio.getImage(), anime.getId());
            System.out.println(animeStudio);
            animeStudioRep.save(animeStudio);
                }
        );




        return "animeEdit";
    }
}
