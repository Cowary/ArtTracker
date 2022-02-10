package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.AnimeRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRoleRep extends CrudRepository<AnimeRole, Long> {

    List<AnimeRole> findByAnimeId(int animeId);
}
