package com.ruderu.mediarecord.controller;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AnimeRepository animeRepository;

    @GetMapping("/title/anime")
    public String getHome(Model model) {
        List<Anime> animeList = animeRepository.findAll();
        for (Anime anime : animeList
             ) {
            System.out.println(anime);
        }
        model.addAttribute("animeList", animeList);
        return "anime";
    }

    @PostMapping("/title/edit")
    public String postEdit(
            @RequestParam Long animeId,
            RedirectAttributes redirectAttributes
    ) {
             redirectAttributes.addAttribute("animeId", animeId);

             return "redirect:./anime/edit?animeId={animeId}";
    }
}
