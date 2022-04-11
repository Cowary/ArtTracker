package com.ruderu.mediarecord.dbCase.movie;

import com.ruderu.mediarecord.entity.movie.MovieIntegration;
import com.ruderu.mediarecord.repo.movie.MovieIntegrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieIntegrationCrud {

    @Autowired
    MovieIntegrationRepo movieIntegrationRepo;

    public void create(long idIntegration, String name, long movieId) {
        MovieIntegration movieIntegration = new MovieIntegration(name, movieId, idIntegration);
        movieIntegrationRepo.save(movieIntegration);
    }
}
