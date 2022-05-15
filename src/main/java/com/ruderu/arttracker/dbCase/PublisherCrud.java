package com.ruderu.arttracker.dbCase;

import com.ruderu.arttracker.entity.Publisher;
import com.ruderu.arttracker.repo.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherCrud {

    @Autowired
    PublisherRepo publisherRepo;

    public Publisher createOrGetByName(String name) {
        return publisherRepo.findByName(name).orElseGet(() -> {
            Publisher publisher = new Publisher(name);
            publisherRepo.save(publisher);
            return publisher;
        });
    }
}
