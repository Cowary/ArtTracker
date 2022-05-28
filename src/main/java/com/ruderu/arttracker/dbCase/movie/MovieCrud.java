package com.ruderu.arttracker.dbCase.movie;

import com.ruderu.arttracker.entity.movie.Movie;
import com.ruderu.arttracker.repo.movie.MovieRepo;
import com.ruderu.arttracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieCrud {

    @Autowired
    MovieRepo movieRepo;

    public List<Movie> getAll(String status) {
        if(status.equals("")) return movieRepo.findAll();
        else return movieRepo.findByStatus(status);
    }

    public Movie findById(long id) {
        return movieRepo.findById(id).orElseThrow();
    }

    public void save(Movie movie) {
        movie.setLastUpd(DateUtil.now());
        movieRepo.save(movie);
    }

    public void delete(Movie movie) {
        movieRepo.delete(movie);
    }
}
