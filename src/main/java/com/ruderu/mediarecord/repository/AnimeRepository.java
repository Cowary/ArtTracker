package com.ruderu.mediarecord.repository;

import com.ruderu.mediarecord.entity.Anime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRepository extends CrudRepository<Anime, Long> {

    List<Anime> findAll();
}
