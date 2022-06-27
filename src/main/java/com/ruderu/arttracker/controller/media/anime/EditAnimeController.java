package com.ruderu.arttracker.controller.media.anime;

import com.ruderu.arttracker.dbCase.StudioCrud;
import com.ruderu.arttracker.dbCase.anime.AnimeCrud;
import com.ruderu.arttracker.dbCase.anime.AnimeStudioCrud;
import com.ruderu.arttracker.entity.Studio;
import com.ruderu.arttracker.entity.anime.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EditAnimeController {

    @Autowired
    AnimeStudioCrud animeStudioCrud;
    @Autowired
    StudioCrud studioCrud;
    @Autowired
    AnimeCrud animeCrud;

    @GetMapping("title/anime/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        Anime anime = animeCrud.getById(id);
        List<Studio> studioModelList = studioCrud.findById(animeStudioCrud.findByAnimeId(id));

        model.addAttribute("anime", anime);
        model.addAttribute("studioList", studioModelList);
        model.addAttribute("edit", true);

        return "media/anime/addAnim";
    }

    @PostMapping("title/anime/edit")
    public String post(
            @ModelAttribute("anime") Anime anime
    ) {
        animeCrud.save(anime);

        return "redirect:../view/media";
    }

    @PostMapping("title/anime/delete")
    public String post(
            @RequestParam() long id
    ) {
        animeCrud.deleteById(id);

        return "redirect:../view/media";
    }
}
