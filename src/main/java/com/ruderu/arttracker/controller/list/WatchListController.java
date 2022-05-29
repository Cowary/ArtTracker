package com.ruderu.arttracker.controller.list;

import com.ruderu.arttracker.dbCase.anime.AnimeCrud;
import com.ruderu.arttracker.dbCase.movie.MovieCrud;
import com.ruderu.arttracker.dbCase.tv.TvCrud;
import com.ruderu.arttracker.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WatchListController {

    @Autowired
    AnimeCrud animeCrud;
    @Autowired
    MovieCrud movieCrud;
    @Autowired
    TvCrud tvCrud;

    @GetMapping("/title/view/watch")
    public String get(@RequestParam(required = false, defaultValue = "") String status,
                      Model model) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeCrud.getAll(status));
        mediaList.addAll(movieCrud.getAll(status));
        mediaList.addAll(tvCrud.getAll(status));

        model.addAttribute("mediaList", mediaList);
        return "media/view/watch";
    }
}
