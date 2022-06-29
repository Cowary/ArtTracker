package com.ruderu.arttracker.repo.manga;

import com.ruderu.arttracker.entity.manga.MangaRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaRoleRep extends CrudRepository<MangaRole, Long> {

    List<MangaRole> findByMangaId(int mangaId);
}
