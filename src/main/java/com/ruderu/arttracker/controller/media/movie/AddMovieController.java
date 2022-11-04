package com.ruderu.arttracker.controller.media.movie;

import com.ruderu.arttracker.dbCase.movie.MovieCrud;
import com.ruderu.arttracker.dbCase.movie.MovieIntegrationCrud;
import com.ruderu.arttracker.dbCase.movie.MovieProductionCrud;
import com.ruderu.arttracker.entity.movie.Movie;
import com.ruderu.arttracker.rest.api.kin.KinApi;
import com.ruderu.arttracker.rest.model.kin.KinFilmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddMovieController {

    @Autowired
    MovieCrud movieCrud;
    @Autowired
    MovieProductionCrud movieProductionCrud;
    @Autowired
    MovieIntegrationCrud movieIntegrationCrud;

    @GetMapping("title/movie/add")
    public String get(
            @RequestParam(required = false) Integer filmId,
            Model model
    ) {
        if(filmId != null) {
            KinFilmModel kinFilmModel = KinApi.filmApi().getById(filmId);
            Movie movie = new Movie(kinFilmModel.getNameOriginal(), kinFilmModel.getNameRu(), kinFilmModel.getYear(), kinFilmModel.getFilmLength());
            model.addAttribute("movie", movie);
            String url = kinFilmModel.getPosterUrl();
            model.addAttribute("add", true);
            model.addAttribute("image", url);
            model.addAttribute("integId", kinFilmModel.getKinopoiskId());
        }
        return "media/movie/add";
    }

    @PostMapping("title/movie/add")
    public String post(
            @ModelAttribute("movie") Movie movie,
            @RequestParam(required = false) String integId,
            RedirectAttributes redirectAttributes
    ) {
        movieCrud.save(movie);

        if(!integId.isBlank()) {
            movieIntegrationCrud.create(Long.parseLong(integId), "kin", movie.getId());
        }
        redirectAttributes.addAttribute("id", movie.getId());

        return "redirect:../movie/edit";
    }


}
