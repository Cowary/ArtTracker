package com.ruderu.arttracker.repo;

import com.ruderu.arttracker.entity.anime.AnimeStudio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeStudioRep extends CrudRepository<AnimeStudio, Long> {

    List<AnimeStudio> findAll();
    List<AnimeStudio> findByAnimeId(Long animeId);
}