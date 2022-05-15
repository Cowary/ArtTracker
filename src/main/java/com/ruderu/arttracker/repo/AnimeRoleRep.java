package com.ruderu.arttracker.repo;

import com.ruderu.arttracker.entity.anime.AnimeRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRoleRep extends CrudRepository<AnimeRole, Long> {

    List<AnimeRole> findByAnimeId(int animeId);
}
