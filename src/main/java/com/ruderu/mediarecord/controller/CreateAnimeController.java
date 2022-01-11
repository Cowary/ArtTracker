package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CreateAnimeController {

    @Autowired
    AnimeRepository animeRepository;

    @GetMapping("/title/anime/add")
    public String get() {
        return "addAnim";
    }

    @PostMapping("/title/anime/add")
    public String post(
            @ModelAttribute("anime") Anime anime
    ) {
        System.out.println(anime.getEndDate());
        animeRepository.save(anime);
        return "redirect:/anime";

    }
}
