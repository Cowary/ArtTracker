package com.ruderu.arttracker.repo.ranobe;

import com.ruderu.arttracker.entity.ranobe.Ranobe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanobeRep extends CrudRepository<Ranobe, Long> {
    List<Ranobe> findAll();
    List<Ranobe> findAllByStatus(String status);
    Ranobe findRanobeByOriginalTitleAndUsrId(String originalTitle, long usrId);
}
