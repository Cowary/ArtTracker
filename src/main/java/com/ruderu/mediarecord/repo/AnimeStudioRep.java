package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.anime.AnimeStudio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeStudioRep extends CrudRepository<AnimeStudio, Long> {

    List<AnimeStudio> findAll();
    List<AnimeStudio> findByAnimeId(Long animeId);
}