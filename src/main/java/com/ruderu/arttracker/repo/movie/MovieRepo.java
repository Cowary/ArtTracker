package com.ruderu.arttracker.repo.movie;

import com.ruderu.arttracker.entity.movie.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MovieRepo extends CrudRepository<Movie, Long> {

    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    List<Movie> findByStatus(String status);
}
