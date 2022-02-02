package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.AnimeStudio;
import com.ruderu.mediarecord.entity.Studio;
import com.ruderu.mediarecord.repository.AnimeStudioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimeStudioCrud {

    @Autowired
    AnimeStudioRep animeStudioRep;
    @Autowired
    StudioCrud studioCrud;

    public void create(String studioName, Long animeId) {
        Studio studio = studioCrud.findOrCreateByName(studioName);
        AnimeStudio animeStudio = new AnimeStudio(animeId, studio.getId());
        animeStudioRep.save(animeStudio);
    }
}
