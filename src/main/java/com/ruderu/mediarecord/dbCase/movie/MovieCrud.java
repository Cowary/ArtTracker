package com.ruderu.mediarecord.dbCase.movie;

import com.ruderu.mediarecord.entity.movie.Movie;
import com.ruderu.mediarecord.repo.movie.MovieRepo;
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
}
