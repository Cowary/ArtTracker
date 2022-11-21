package com.ruderu.arttracker.controller.media.anime;

import com.ruderu.arttracker.dbCase.anime.AnimeCrud;
import com.ruderu.arttracker.dbCase.anime.AnimeRoleCrud;
import com.ruderu.arttracker.dbCase.anime.AnimeStudioCrud;
import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.integration.api.shiki.ShikimoriApi;
import com.ruderu.arttracker.integration.model.shiki.AnimeModel;
import com.ruderu.arttracker.integration.model.shiki.StudioModel;
import com.ruderu.arttracker.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddAnimeController {

    @Autowired
    AnimeCrud animeCrud;
    @Autowired
    AnimeStudioCrud animeStudioCrud;
    @Autowired
    AnimeRoleCrud animeRoleCrud;

    @GetMapping("title/anime/add")
    public String get(
            @RequestParam(required = false) Integer animeId,
            Model model
    ) {
        if(animeId != null) {
            AnimeModel animeModel = ShikimoriApi.animeApi().getById(animeId);

            Anime anime = new Anime(animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), (long) animeModel.getId(), animeModel.getDuration());
            List<StudioModel> studioModelList = List.of(animeModel.getStudios());
            model.addAttribute("anime", anime);
            model.addAttribute("studioList", studioModelList);
            String url = "https://dere.shikimori.one" + animeModel.getImage().getOriginal();
            model.addAttribute("add", true);
            model.addAttribute("image", url);
        }

        return "media/anime/addAnim";
    }

    @PostMapping("title/anime/add")
    public String post(
            @ModelAttribute("anime") Anime anime,
            RedirectAttributes redirectAttributes
    ) {
        AnimeModel animeModel = ShikimoriApi.animeApi().getById(Math.toIntExact(anime.getShikiId()));

        List<StudioModel> studioModelList = List.of(animeModel.getStudios());

        animeCrud.save(anime);
        studioModelList.forEach(studioModel -> animeStudioCrud.create(studioModel.getName(), anime.getId())
        );
        animeRoleCrud.create(anime.getId(), animeModel.getRoleModels());
        redirectAttributes.addAttribute("id", anime.getId());

        return "redirect:../anime/edit";
    }
}
