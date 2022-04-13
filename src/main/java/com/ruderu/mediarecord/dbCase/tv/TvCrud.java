package com.ruderu.mediarecord.dbCase.tv;

import com.ruderu.mediarecord.entity.tv.Tv;
import com.ruderu.mediarecord.repo.tv.TvRepo;
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
