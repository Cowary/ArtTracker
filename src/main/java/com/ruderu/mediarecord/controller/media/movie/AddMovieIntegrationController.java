package com.ruderu.mediarecord.controller.media.movie;

import com.ruderu.mediarecord.dbCase.movie.MovieIntegrationCrud;
import com.ruderu.mediarecord.dbCase.movie.MovieProductionCrud;
import com.ruderu.mediarecord.entity.movie.Movie;
import com.ruderu.mediarecord.model.tmdb.MovieModel;
import com.ruderu.mediarecord.model.tmdb.ProductionCompanyModel;
import com.ruderu.mediarecord.repo.movie.MovieRepo;
import com.ruderu.mediarecord.rest.TmdbApi;
import com.ruderu.mediarecord.util.DateFormat;
import com.ruderu.mediarecord.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class AddMovieIntegrationController {

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    MovieProductionCrud movieProductionCrud;
    @Autowired
    MovieIntegrationCrud movieIntegrationCrud;

    @GetMapping("title/movie/addIntegration")
    public String get(
            @RequestParam int id,
            Model model
    ) {
        MovieModel movieModel = TmdbApi.searchMovieById(id);
        FileUtil.downloadFile("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movieModel.getPosterPath(), "image");

        Movie movie = new Movie(
                movieModel.getOriginalTitle(),
                movieModel.getTitle(),
                movieModel.getReleaseDate(),
                movieModel.getRuntime());

        List<ProductionCompanyModel> productionCompanyModelList = List.of(movieModel.getProductionCompanies());

        model.addAttribute("movie", movie);
        model.addAttribute("movieReleaseDate", DateFormat.HTMLshort.format(movieModel.getReleaseDate()));
        String url = "/resources/images/image.jpeg";
        model.addAttribute("image", url);
        model.addAttribute("tmdbId", movieModel.getId());
        model.addAttribute("productionCompanyModelList", productionCompanyModelList);
        System.out.println(movie);

        return "media/movie/addIntegration";
    }

    @PostMapping("title/movie/addIntegration")
    public String post(
            @ModelAttribute("movie") Movie movie,
            @RequestParam(required = false) @DateTimeFormat(pattern = DateFormat.HTMLshort_PATTER) Date endDate,
            @RequestParam(required = false) String comment,
            @RequestParam(required = false) long tmdbId
    ) {
        System.out.println(movie.toString());

        MovieModel movieModel = TmdbApi.searchMovieById(Math.toIntExact(tmdbId));
        if(endDate != null) {
            movie.setEndDate(endDate);
        }
        if(movie.getComment().isEmpty()) {
            movie.setComment(null);
        }
        if(!comment.isEmpty()) {
            movie.setComment(comment);
        }
        movie.setId(null);
        movieRepo.save(movie);
        movieIntegrationCrud.create(movieModel.getId(), "tmdb", movie.getId());
        List<ProductionCompanyModel> productionCompanyModelList = List.of(movieModel.getProductionCompanies());
        productionCompanyModelList.forEach(
                productionCompanyModel -> movieProductionCrud.create(productionCompanyModel.getName(), movie.getId())
        );

        return "media/movie/addIntegration";
    }
}
