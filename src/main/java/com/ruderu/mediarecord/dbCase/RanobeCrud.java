package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.Ranobe;
import com.ruderu.mediarecord.repo.RanobeRep;
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
}
