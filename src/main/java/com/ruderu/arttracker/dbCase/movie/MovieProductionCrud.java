package com.ruderu.arttracker.dbCase.movie;

import com.ruderu.arttracker.dbCase.ProductionCrud;
import com.ruderu.arttracker.entity.Production;
import com.ruderu.arttracker.entity.movie.MovieProduction;
import com.ruderu.arttracker.repo.movie.MovieProductionRepo;
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
