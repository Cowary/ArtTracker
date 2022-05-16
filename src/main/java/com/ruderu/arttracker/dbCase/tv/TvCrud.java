package com.ruderu.arttracker.dbCase.tv;

import com.ruderu.arttracker.entity.tv.Tv;
import com.ruderu.arttracker.repo.tv.TvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TvCrud {

    @Autowired
    TvRepo tvRepo;

    public List<Tv> getAll(String status) {
        if(status.equals("")) return tvRepo.findAll();
        else return tvRepo.findByStatus(status);
    }
}