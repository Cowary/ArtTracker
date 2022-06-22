package com.ruderu.arttracker.dbCase.movie;

import com.ruderu.arttracker.dbCase.UserService;
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
    @Autowired
    UserService userService;

    public List<Movie> getAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return movieRepo.findAllByUsrId(userId);
        else return movieRepo.findByStatus(status);
    }

    public Movie findById(long id) {
        return movieRepo.findById(id).orElseThrow();
    }

    public void save(Movie movie) {
        movie.setLastUpd(DateUtil.now());
        movie.setUsrId(userService.getIdCurrentUser());
        movieRepo.save(movie);
    }

    public void delete(Movie movie) {
        movieRepo.delete(movie);
    }
}
