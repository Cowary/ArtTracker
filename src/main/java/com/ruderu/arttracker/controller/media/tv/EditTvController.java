package com.ruderu.arttracker.controller.media.tv;

import com.ruderu.arttracker.dbCase.tv.TvCrud;
import com.ruderu.arttracker.dbCase.tv.TvSeasonsCrud;
import com.ruderu.arttracker.entity.tv.Tv;
import com.ruderu.arttracker.entity.tv.TvSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditTvController {

    @Autowired
    TvCrud tvCrud;
    @Autowired
    TvSeasonsCrud tvSeasonsCrud;

    @GetMapping("title/tv/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        TvSeason tvSeason = tvSeasonsCrud.getById(id);
        Tv tv = tvCrud.findById(tvSeason.getTvId());
        //Tv tv = tvCrud.findById(id);

        model.addAttribute(tvSeason);
        model.addAttribute("titleSeason", tvSeason.getTitle());
        model.addAttribute("tv", tv);
        model.addAttribute("edit", true);

        return "media/tv/add";
    }

    @PostMapping("title/tv/edit")
    public String post(
            @ModelAttribute("tv") Tv tv,
            @ModelAttribute("tvSeason") TvSeason tvSeason,
            @RequestParam("titleSeason") String titleSeason
    ) {
        tvSeason.setTitle(titleSeason);
        tv.setId(tvSeasonsCrud.getTvIdById(tvSeason.getId()));
        tvSeasonsCrud.save(tvSeason, tv);

        return "redirect:../view/media";
    }

    @PostMapping("title/tv/delete")
    public String post(
            @RequestParam() long id
    ) {
        TvSeason tvSeason = tvSeasonsCrud.getById(id);
        tvSeasonsCrud.delete(tvSeason);

        return "redirect:../view/media";
    }
}
