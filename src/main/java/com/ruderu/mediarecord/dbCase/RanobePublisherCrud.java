package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.Publisher;
import com.ruderu.mediarecord.entity.RanobePublisher;
import com.ruderu.mediarecord.repo.RanobePublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RanobePublisherCrud {

    @Autowired
    RanobePublisherRepo ranobePublisherRepo;
    @Autowired
    PublisherCrud publisherCrud;

    public void create(long ranobeId, List<String> publishers) {
        List<RanobePublisher> ranobePublishers = new ArrayList<>();
        for (String publisherName : publishers) {
            Publisher publisher = publisherCrud.createOrGetByName(publisherName);
            ranobePublishers.add(new RanobePublisher(ranobeId, publisher.getId()));
        }
        ranobePublisherRepo.saveAll(ranobePublishers);
    }
}
