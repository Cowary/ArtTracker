package com.ruderu.arttracker.dbCase.ranobe;

import com.ruderu.arttracker.entity.ranobe.Ranobe;
import com.ruderu.arttracker.repo.RanobeRep;
import com.ruderu.arttracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RanobeCrud {

    @Autowired
    RanobeRep ranobeRep;

    public List<Ranobe> getAll(String status) {
        if(status.equals("")) return ranobeRep.findAll();
        else return ranobeRep.findAllByStatus(status);
    }

    public void save(Ranobe ranobe) {
        ranobe.setLastUpd(DateUtil.now());
        ranobeRep.save(ranobe);
    }
}
