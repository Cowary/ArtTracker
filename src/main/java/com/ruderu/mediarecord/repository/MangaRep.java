package com.ruderu.mediarecord.repository;

import com.ruderu.mediarecord.entity.Manga;
import org.springframework.data.repository.CrudRepository;

public interface MangaRep extends CrudRepository<Manga, Long> {
}
