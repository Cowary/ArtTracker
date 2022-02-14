package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.Anime;
import com.ruderu.mediarecord.repo.AnimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimeCrud {

    @Autowired
    AnimeRepo animeRepo;

    public List<Anime> getAll(String status) {
        if(status.equals("")) return animeRepo.findAll();
        else return animeRepo.findByStatus(status);
    }
}
