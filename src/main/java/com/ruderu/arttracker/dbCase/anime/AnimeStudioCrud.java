package com.ruderu.arttracker.dbCase.anime;

import com.ruderu.arttracker.dbCase.StudioCrud;
import com.ruderu.arttracker.entity.Studio;
import com.ruderu.arttracker.entity.anime.AnimeStudio;
import com.ruderu.arttracker.repo.AnimeStudioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimeStudioCrud {

    @Autowired
    AnimeStudioRep animeStudioRep;
    @Autowired
    StudioCrud studioCrud;

    public void create(String studioName, Long animeId) {
        Studio studio = studioCrud.createOrGetByName(studioName);
        AnimeStudio animeStudio = new AnimeStudio(animeId, studio.getId());
        animeStudioRep.save(animeStudio);
    }

    public List<AnimeStudio> findByAnimeId(Long animeId) {
        return animeStudioRep.findByAnimeId(animeId);
    }
}
