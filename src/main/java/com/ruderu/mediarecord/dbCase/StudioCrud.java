package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.Studio;
import com.ruderu.mediarecord.repository.StudioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudioCrud {

    @Autowired
    StudioRep studioRep;

    public Studio findOrCreateByName(String name) {
        Studio studio = studioRep.findByName(name).orElse(new Studio(name));
        studioRep.save(studio);
        return studio;
    }

    public Studio findById(Long id) {
        return studioRep.findById(id).orElseThrow();
    }
}
