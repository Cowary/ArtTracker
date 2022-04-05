package com.ruderu.mediarecord.controller.media.movie;

import com.ruderu.mediarecord.model.tmdb.MovieModel;
import com.ruderu.mediarecord.rest.TmdbApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddMovieController {

    public String get(
            @RequestParam int id,
            Model model
    ) {
        MovieModel movieModel = TmdbApi.searchMovieById(id);

        return "";
    }
}
