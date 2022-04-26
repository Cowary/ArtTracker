package com.ruderu.mediarecord.repo;

import com.ruderu.mediarecord.entity.ranobe.Ranobe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanobeRep extends CrudRepository<Ranobe, Long> {
    List<Ranobe> findAll();
    List<Ranobe> findAllByStatus(String status);
}
