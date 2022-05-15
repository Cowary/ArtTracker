package com.ruderu.arttracker.controller.media.movie;

import com.ruderu.arttracker.model.tmdb.ResultModel;
import com.ruderu.arttracker.rest.TmdbApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FindMovieIntegrationController {

    @GetMapping("/title/movie/find")
    public String get() {
        return "media/movie/find";
    }

    @PostMapping("/title/movie/find")
    public String post(
            @ModelAttribute("movieName") String movieName,
            Model model
    ) {
        List<ResultModel> list = TmdbApi.searchFilm(movieName);
        model.addAttribute("list", list);

        return "media/movie/find";
    }

    @PostMapping("/title/movie/save")
    public String post(
            @RequestParam int id,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("id", id);

        return "redirect:./addIntegration";
    }
}
