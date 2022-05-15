package com.ruderu.arttracker.repo;

import com.ruderu.arttracker.entity.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublisherRepo extends CrudRepository<Publisher, Long> {

    Optional<Publisher> findByName(String name);
}
