package com.ruderu.mediarecord.repository;

import com.ruderu.mediarecord.entity.Manga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaRep extends CrudRepository<Manga, Long> {
    List<Manga> findAll();
}
