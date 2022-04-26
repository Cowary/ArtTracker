package com.ruderu.mediarecord.dbCase.ranobe;

import com.ruderu.mediarecord.dbCase.PublisherCrud;
import com.ruderu.mediarecord.entity.Publisher;
import com.ruderu.mediarecord.entity.ranobe.RanobePublisher;
import com.ruderu.mediarecord.model.shiki.PublisherModel;
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

    public void create(long ranobeId, List<PublisherModel> publishers) {
        List<RanobePublisher> ranobePublishers = new ArrayList<>();
        for (PublisherModel publisherModel : publishers) {
            Publisher publisher = publisherCrud.createOrGetByName(publisherModel.getName());
            ranobePublishers.add(new RanobePublisher(ranobeId, publisher.getId()));
        }
        ranobePublisherRepo.saveAll(ranobePublishers);
    }
}
