package com.ruderu.arttracker.controller.media.anime;

import com.ruderu.arttracker.dbCase.StudioCrud;
import com.ruderu.arttracker.dbCase.anime.AnimeStudioCrud;
import com.ruderu.arttracker.entity.Studio;
import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.repo.AnimeRepo;
import com.ruderu.arttracker.util.DateFormat;
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
    AnimeRepo animeRepo;
    @Autowired
    AnimeStudioCrud animeStudioCrud;
    @Autowired
    StudioCrud studioCrud;

    @GetMapping("title/anime/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        Anime anime = animeRepo.findById(id).orElseThrow();
        List<Studio> studioModelList = studioCrud.findById(animeStudioCrud.findByAnimeId(id));

        model.addAttribute("anime", anime);
        model.addAttribute("studioList", studioModelList);
        model.addAttribute("edit", true);
        System.out.println(DateFormat.HTML.format(anime.getReleaseDate()));
        return "media/anime/addAnim";
    }

    @PostMapping("title/anime/edit")
    public String post(
            @ModelAttribute("anime") Anime anime
    ) {
        System.out.println(anime);
        animeRepo.save(anime);
        return "redirect:../view/media";
    }

    @PostMapping("title/anime/delete")
    public String post(
            @RequestParam() long id
    ) {
        Anime anime = animeRepo.findById(id).orElseThrow();
        animeRepo.delete(anime);

        return "redirect:../view/media";
    }
}
