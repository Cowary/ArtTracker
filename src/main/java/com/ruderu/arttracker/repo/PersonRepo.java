package com.ruderu.arttracker.repo;

import com.ruderu.arttracker.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepo extends CrudRepository<Person, Long> {

    Optional<Person> findByNameEn(String nameEn);
}
