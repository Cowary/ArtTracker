package com.ruderu.arttracker.repo.tv;

import com.ruderu.arttracker.entity.tv.Tv;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TvRepo extends CrudRepository<Tv, Long> {

    List<Tv> findAll();
    Optional<Tv> findById(Long id);
    Optional<Tv> findByOriginalTitle(String originalTitle);
    List<Tv> findByStatus(String status);

}
