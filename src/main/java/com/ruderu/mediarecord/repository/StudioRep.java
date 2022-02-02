package com.ruderu.mediarecord.repository;

import com.ruderu.mediarecord.entity.Studio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudioRep extends CrudRepository<Studio, Long> {

    List<Studio> findAll();
    Optional<Studio> findByName(String name);
    Optional<Studio> findById(Long id);
}
