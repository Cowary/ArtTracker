package com.ruderu.arttracker.dbCase.tv;

import com.ruderu.arttracker.entity.tv.TvIntegration;
import com.ruderu.arttracker.repo.tv.TvIntegrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TvIntegrationCrud {

    @Autowired
    TvIntegrationRepo tvIntegrationRepo;

    public void create(String name, long tvId, long idIntegration) {
        TvIntegration tvIntegration = new TvIntegration(name, tvId, idIntegration);
        tvIntegrationRepo.save(tvIntegration);
    }
}
