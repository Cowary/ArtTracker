package com.ruderu.mediarecord.controller.media.movie;

import com.ruderu.mediarecord.dbCase.movie.MovieIntegrationCrud;
import com.ruderu.mediarecord.dbCase.movie.MovieProductionCrud;
import com.ruderu.mediarecord.entity.movie.Movie;
import com.ruderu.mediarecord.repo.movie.MovieRepo;
import com.ruderu.mediarecord.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            Model model
    ) {
        return "media/movie/add";
    }

    @PostMapping("title/movie/add")
    public String post(
            @ModelAttribute("movie") Movie movie,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam(required = false) String studio
    ) {

        if(endDate != null) {
            movie.setEndDate(endDate);
        }

        movie.setId(null);
        System.out.println(movie);
        movieRepo.save(movie);
        if(!studio.isEmpty()) {
            List<String> studioList = Arrays
                    .stream(studio.split("/"))
                    .map(String::trim)
                    .collect(Collectors.toList());
            studioList.forEach(
                    s -> movieProductionCrud.create(s, movie.getId())
            );
        }

        return "media/movie/add";
    }


}
