package com.ruderu.arttracker.controller.media.tv;

import com.ruderu.arttracker.dbCase.tv.TvIntegrationCrud;
import com.ruderu.arttracker.entity.tv.Tv;
import com.ruderu.arttracker.model.kin.KinFilmModel;
import com.ruderu.arttracker.repo.tv.TvRepo;
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
    private TvRepo tvRepo;
    @Autowired
    private TvIntegrationCrud tvIntegrationCrud;

    @GetMapping("title/tv/add")
    public String get(
            @RequestParam(required = false) Integer filmId,
            Model model
    ) {
        if(filmId != 0) {
            KinFilmModel kinFilmModel = KinApi.searchById(filmId);
            int seasonsTotal = KinApi.totalSeasons(filmId);
            Tv tv = new Tv(kinFilmModel.getNameOriginal(), kinFilmModel.getNameRu(), kinFilmModel.getYear(), seasonsTotal);
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
            @RequestParam(required = false) String integId
    ) {
        tvRepo.save(tv);
        System.out.println(tv);
        if(!integId.isBlank()) {
            tvIntegrationCrud.create("kin", tv.getId(), Long.parseLong(integId));
        }

        return "media/tv/add";
    }
}
