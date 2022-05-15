package com.ruderu.arttracker.controller;

import com.ruderu.arttracker.dbCase.anime.AnimeCrud;
import com.ruderu.arttracker.dbCase.book.BookCrud;
import com.ruderu.arttracker.dbCase.game.GameCrud;
import com.ruderu.arttracker.dbCase.manga.MangaCrud;
import com.ruderu.arttracker.dbCase.movie.MovieCrud;
import com.ruderu.arttracker.dbCase.ranobe.RanobeCrud;
import com.ruderu.arttracker.dbCase.tv.TvCrud;
import com.ruderu.arttracker.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MediaListController {

    @Autowired
    AnimeCrud animeCrud;
    @Autowired
    MangaCrud mangaCrud;
    @Autowired
    RanobeCrud ranobeCrud;
    @Autowired
    MovieCrud movieCrud;
    @Autowired
    GameCrud gameCrud;
    @Autowired
    BookCrud bookCrud;
    @Autowired
    TvCrud tvCrud;

    @GetMapping("/title/view/media")
    public String get(@RequestParam(required = false, defaultValue = "") String status,
            Model model) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeCrud.getAll(status));
        mediaList.addAll(mangaCrud.getAll(status));
        mediaList.addAll(ranobeCrud.getAll(status));
        mediaList.addAll(movieCrud.getAll(status));
        mediaList.addAll(gameCrud.getAll(status));
        mediaList.addAll(bookCrud.getAll(status));
        mediaList.addAll(tvCrud.getAll(status));

        model.addAttribute("mediaList", mediaList);
        return "media/view/mediaList";
    }

    @GetMapping("/title/view/edit")
    public String post(
            @RequestParam int id,
            @RequestParam String type,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("id", id);

        return "redirect:../" + type.toLowerCase() + "/edit";
    }
}
