package com.ruderu.mediarecord.dbCase.movie;

import com.ruderu.mediarecord.dbCase.ProductionCrud;
import com.ruderu.mediarecord.entity.Production;
import com.ruderu.mediarecord.entity.movie.MovieProduction;
import com.ruderu.mediarecord.repo.movie.MovieProductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieProductionCrud {

    @Autowired
    MovieProductionRepo movieProductionRepo;
    @Autowired
    ProductionCrud productionCrud;

    public void create(String productionName, Long movieId) {
        Production production = productionCrud.createOrGetByName(productionName);
        MovieProduction movieProduction = new MovieProduction(movieId, production.getId());
        movieProductionRepo.save(movieProduction);
    }
}
