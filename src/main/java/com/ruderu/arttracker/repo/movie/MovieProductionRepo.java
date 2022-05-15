package com.ruderu.arttracker.repo.movie;

import com.ruderu.arttracker.entity.movie.MovieProduction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieProductionRepo extends CrudRepository<MovieProduction, Long> {

    List<MovieProduction> findById(long id);
}
