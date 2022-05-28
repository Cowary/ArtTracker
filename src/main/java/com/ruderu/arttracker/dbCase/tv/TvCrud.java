package com.ruderu.arttracker.dbCase.tv;

import com.ruderu.arttracker.entity.tv.Tv;
import com.ruderu.arttracker.repo.tv.TvRepo;
import com.ruderu.arttracker.util.DateUtil;
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

    public Tv findById(long id) {
        return tvRepo.findById(id).orElseThrow();
    }

    public void save(Tv tv) {
        tv.setLastUpd(DateUtil.now());
        tvRepo.save(tv);
    }

    public void delete(Tv tv) {
        tvRepo.delete(tv);
    }
}
