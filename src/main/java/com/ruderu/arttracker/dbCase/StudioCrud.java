package com.ruderu.arttracker.dbCase;

import com.ruderu.arttracker.entity.Studio;
import com.ruderu.arttracker.entity.anime.AnimeStudio;
import com.ruderu.arttracker.repo.StudioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudioCrud {

    @Autowired
    StudioRep studioRep;

    public Studio createOrGetByName(String name) {
        return studioRep.findByName(name)
                .orElseGet(() -> {
                    Studio studio = new Studio(name);
                    studioRep.save(studio);
                    return studio;
        });
    }

    public Studio findById(Long id) {
        return studioRep.findById(id).orElseThrow();
    }

    public List<Studio> findById(List<AnimeStudio> longList) {
        return longList.stream()
                .map(lg -> studioRep.findById(lg.getStudioId()).orElseThrow())
                .collect(Collectors.toList());
    }
}
