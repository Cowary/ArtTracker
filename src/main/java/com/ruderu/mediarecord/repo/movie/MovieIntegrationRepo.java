package com.ruderu.mediarecord.repo.movie;

import com.ruderu.mediarecord.entity.movie.MovieIntegration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieIntegrationRepo extends CrudRepository<MovieIntegration, Long> {

    Optional<MovieIntegration> findByNameAndIdIntegration(String name, Long integrationId);
}
