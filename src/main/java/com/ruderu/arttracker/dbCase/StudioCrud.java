package com.ruderu.arttracker.dbCase;

import com.ruderu.arttracker.entity.Studio;
import com.ruderu.arttracker.repo.StudioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
