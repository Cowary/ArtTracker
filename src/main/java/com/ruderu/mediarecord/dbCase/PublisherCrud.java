package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.Publisher;
import com.ruderu.mediarecord.repo.PublisherRepo;
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
