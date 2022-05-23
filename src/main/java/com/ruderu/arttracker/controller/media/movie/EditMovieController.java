package com.ruderu.arttracker.controller.media.movie;

import com.ruderu.arttracker.dbCase.movie.MovieCrud;
import com.ruderu.arttracker.entity.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditMovieController {

    @Autowired
    MovieCrud movieCrud;

    @GetMapping("title/movie/edit")
    public String get(
            @RequestParam long id,
            Model model
    ) {
        Movie movie = movieCrud.findById(id);

        model.addAttribute("movie", movie);
        model.addAttribute("edit", true);

        return "media/movie/add";
    }

    @PostMapping("title/movie/edit")
    public String post(
            @ModelAttribute("movie") Movie movie
    ) {
        movieCrud.save(movie);

        return "redirect:../view/media";
    }

    @PostMapping("title/movie/delete")
    public String post(
            @RequestParam() long id
    ) {
        Movie movie = movieCrud.findById(id);
        movieCrud.delete(movie);

        return "redirect:../view/media";
    }
}
