package com.ruderu.mediarecord.repository;

import com.ruderu.mediarecord.entity.AnimeStudio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeStudioRep extends CrudRepository<AnimeStudio, Long> {

    List<AnimeStudio> findAll();
}