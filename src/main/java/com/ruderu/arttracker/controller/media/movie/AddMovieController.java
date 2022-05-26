package com.ruderu.arttracker.controller.media.movie;

import com.ruderu.arttracker.dbCase.movie.MovieIntegrationCrud;
import com.ruderu.arttracker.dbCase.movie.MovieProductionCrud;
import com.ruderu.arttracker.entity.movie.Movie;
import com.ruderu.arttracker.model.kin.KinFilmModel;
import com.ruderu.arttracker.repo.movie.MovieRepo;
import com.ruderu.arttracker.rest.KinApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddMovieController {

    @Autowired
    MovieRepo movieRepo;
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
            KinFilmModel kinFilmModel = KinApi.searchById(filmId);
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
            @RequestParam(required = false) String integId
    ) {
        movieRepo.save(movie);

        if(!integId.isBlank()) {
            movieIntegrationCrud.create(Long.parseLong(integId), "kin", movie.getId());
        }

        return "media/movie/add";
    }


}
