package com.ruderu.arttracker.dbCase.anime;

import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.repo.AnimeRepo;
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
