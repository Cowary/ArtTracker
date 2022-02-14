package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.Manga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaRep extends CrudRepository<Manga, Long> {
    List<Manga> findAll();
    List<Manga> findAllByStatus(String status);
}
