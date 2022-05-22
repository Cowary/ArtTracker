package com.ruderu.arttracker.controller.media.anime;

import com.ruderu.arttracker.dbCase.anime.AnimeRoleCrud;
import com.ruderu.arttracker.dbCase.anime.AnimeStudioCrud;
import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.model.shiki.AnimeModel;
import com.ruderu.arttracker.model.shiki.StudioModel;
import com.ruderu.arttracker.repo.AnimeRepo;
import com.ruderu.arttracker.repo.AnimeStudioRep;
import com.ruderu.arttracker.rest.ShikimoriApi;
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
public class AddAnimeController {

    @Autowired
    AnimeRepo animeRepo;
    @Autowired
    AnimeStudioRep animeStudioRep;
    @Autowired
    AnimeStudioCrud animeStudioCrud;
    @Autowired
    AnimeRoleCrud animeRoleCrud;

    @GetMapping("title/anime/add")
    public String get(
            @RequestParam int animeId,
            Model model
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(animeId);

        Anime anime = new Anime(animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), (long) animeModel.getId());
        List<StudioModel> studioModelList = List.of(animeModel.getStudios());
        model.addAttribute("anime", anime);
        model.addAttribute("studioList", studioModelList);
        String url = "https://dere.shikimori.one" + animeModel.getImage().getOriginal();
        model.addAttribute("add", true);
        model.addAttribute("image", url);
        System.out.println(DateFormat.HTML.format(anime.getReleaseDate()));

        return "media/anime/addAnim";
    }

    @PostMapping("title/anime/add")
    public String post(
            @ModelAttribute("anime") Anime anime
    ) {
        AnimeModel animeModel = ShikimoriApi.findById(Math.toIntExact(anime.getShikiId()));
        anime.setDuration(animeModel.getDuration());

        List<StudioModel> studioModelList = List.of(animeModel.getStudios());

        animeRepo.save(anime);
        studioModelList.forEach(studioModel -> animeStudioCrud.create(studioModel.getName(), anime.getId())
        );
        animeRoleCrud.create(anime.getId(), animeModel.getRoleModels());
        return "media/anime/addAnim";
    }
}
