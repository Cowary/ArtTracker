package com.ruderu.arttracker.repo.tv;

import com.ruderu.arttracker.entity.tv.TvIntegration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TvIntegrationRepo extends CrudRepository<TvIntegration, Long> {

    Optional<TvIntegration> findByIdTv(Long tvId);
}
