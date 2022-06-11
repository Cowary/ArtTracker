package com.ruderu.arttracker.controller.media.tv;

import com.ruderu.arttracker.dbCase.tv.TvCrud;
import com.ruderu.arttracker.dbCase.tv.TvIntegrationCrud;
import com.ruderu.arttracker.dbCase.tv.TvSeasonsCrud;
import com.ruderu.arttracker.entity.tv.Tv;
import com.ruderu.arttracker.entity.tv.TvSeason;
import com.ruderu.arttracker.model.kin.KinFilmModel;
import com.ruderu.arttracker.rest.KinApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddTvController {

    @Autowired
    private TvIntegrationCrud tvIntegrationCrud;
    @Autowired
    private TvCrud tvCrud;
    @Autowired
    private TvSeasonsCrud tvSeasonsCrud;

    @GetMapping("title/tv/add")
    public String get(
            @RequestParam(required = false) Integer filmId,
            Model model
    ) {
        if(filmId != null) {
            KinFilmModel kinFilmModel = KinApi.searchById(filmId);
            int seasonsTotal = KinApi.totalSeasons(filmId);
            Tv tv = new Tv(kinFilmModel.getNameOriginal(), kinFilmModel.getNameRu(), kinFilmModel.getYear(), seasonsTotal);
            Tv tvSql = tvCrud.findByOriginalTitle(tv.getOriginalTitle());
            if(tvSql != null) tv = tvSql;

            model.addAttribute(tv);
            String url = kinFilmModel.getPosterUrl();
            model.addAttribute("add", true);
            model.addAttribute("image", url);
            model.addAttribute("integId", kinFilmModel.getKinopoiskId());
        }
        return "media/tv/add";
    }

    @PostMapping("title/tv/add")
    public String post(
            @ModelAttribute("tv") Tv tv,
            @ModelAttribute("tv_seasons") TvSeason tvSeason,
            @RequestParam("titleSeason") String titleSeason,
            @RequestParam(required = false) String integId
    ) {
        System.out.println(tv);
        tvSeason.setTitle(titleSeason);
        tvCrud.save(tv);

        if(!integId.isBlank() && tv.getId() != null && tvIntegrationCrud.findByTvId(tv.getId()) == null) {
            tvIntegrationCrud.create("kin", tv.getId(), Long.parseLong(integId));
        }
        tvSeasonsCrud.save(tvSeason, tv);

        return "media/tv/add";
    }
}
