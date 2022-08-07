package com.ruderu.arttracker.controller.media.movie;

import com.ruderu.arttracker.rest.api.kin.KinApi;
import com.ruderu.arttracker.rest.model.kin.KinResultModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FindMovieController {

    @GetMapping("/title/movie/find")
    public String get() {
        return "media/movie/find";
    }

    @PostMapping("/title/movie/find")
    public String post(
            @RequestParam String keyword,
            Model model
    ) {
        List<KinResultModel> list = KinApi.filmApi().searchByKeyword(keyword);
        model.addAttribute("list", list);

        return "media/movie/find";

    }

    @PostMapping("/title/movie/save")
    public String post(
            @RequestParam int filmId,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addAttribute("filmId", filmId);

        return "redirect:./add";
    }
}
