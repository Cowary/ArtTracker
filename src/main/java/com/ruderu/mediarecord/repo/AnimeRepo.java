package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.Anime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnimeRepo extends CrudRepository<Anime, Long> {

    List<Anime> findAll();
    Optional<Anime> findById(Long id);
    List<Anime> findByStatus(String status);
}
