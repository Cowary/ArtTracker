package com.ruderu.arttracker.dbCase;

import com.ruderu.arttracker.entity.Production;
import com.ruderu.arttracker.repo.ProductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductionCrud {

    @Autowired
    ProductionRepo productionRepo;

    public Production createOrGetByName(String name) {
        return productionRepo.findByName(name)
                .orElseGet(() -> {
                    Production production = new Production(name);
                    productionRepo.save(production);
                    return production;
        });
    }
}
