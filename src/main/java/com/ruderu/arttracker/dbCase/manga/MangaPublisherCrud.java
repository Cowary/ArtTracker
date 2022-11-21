package com.ruderu.arttracker.dbCase.manga;

import com.ruderu.arttracker.dbCase.PublisherCrud;
import com.ruderu.arttracker.entity.Publisher;
import com.ruderu.arttracker.entity.manga.MangaPublisher;
import com.ruderu.arttracker.integration.model.shiki.PublisherModel;
import com.ruderu.arttracker.repo.manga.MangaPublisherRepo;
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

    public void create(long mangaId, List<PublisherModel> publishers) {
        List<MangaPublisher> mangaPublishers = new ArrayList<>();
        for (PublisherModel publisherModel : publishers) {
            Publisher publisher = publisherCrud.createOrGetByName(publisherModel.getName());
            mangaPublishers.add(new MangaPublisher(mangaId, publisher.getId()));
        }
        mangaPublisherRepo.saveAll(mangaPublishers);
    }
}
