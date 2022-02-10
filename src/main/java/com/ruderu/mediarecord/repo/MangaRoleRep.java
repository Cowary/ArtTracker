package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.MangaRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaRoleRep extends CrudRepository<MangaRole, Long> {

    List<MangaRole> findByMangaId(int mangaId);
}
