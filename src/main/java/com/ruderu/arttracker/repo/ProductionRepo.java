package com.ruderu.arttracker.repo;

import com.ruderu.arttracker.entity.Production;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductionRepo extends CrudRepository<Production, Long> {

    Optional<Production> findByName(String name);
}
