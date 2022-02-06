package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Media;
import com.ruderu.mediarecord.repository.AnimeRepository;
import com.ruderu.mediarecord.repository.MangaRep;
import com.ruderu.mediarecord.repository.RanobeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MediaListController {

    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private MangaRep mangaRep;
    @Autowired
    private RanobeRep ranobeRep;

    @GetMapping("/title/media")
    public String get(Model model) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeRepository.findAll());
        mediaList.addAll(mangaRep.findAll());
        mediaList.addAll(ranobeRep.findAll());
        model.addAttribute("mediaList", mediaList);
        return "mediaList";
    }
}
