package com.ruderu.arttracker.repo.movie;

import com.ruderu.arttracker.entity.movie.MovieIntegration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieIntegrationRepo extends CrudRepository<MovieIntegration, Long> {

    Optional<MovieIntegration> findByNameAndIdIntegration(String name, Long integrationId);
}
