package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.MangaPublisher;
import com.ruderu.mediarecord.entity.Publisher;
import com.ruderu.mediarecord.repo.MangaPublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MangaPublisherCrud {

    @Autowired
    MangaPublisherRepo mangaPublisherRepo;
    @Autowired
    PublisherCrud publisherCrud;

    public void create(long mangaId, List<String> publishers) {
        List<MangaPublisher> mangaPublishers = new ArrayList<>();
        for (String publisherName : publishers) {
            Publisher publisher = publisherCrud.createOrGetByName(publisherName);
            mangaPublishers.add(new MangaPublisher(mangaId, publisher.getId()));
        }
        mangaPublisherRepo.saveAll(mangaPublishers);
    }
}
