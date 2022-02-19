package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.MangaPublisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaPublisherRepo extends CrudRepository<MangaPublisher, Long> {

    List<MangaPublisher> findByMangaId(long mangaId);
}