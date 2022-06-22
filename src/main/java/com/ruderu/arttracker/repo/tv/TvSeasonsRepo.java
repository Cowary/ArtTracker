package com.ruderu.arttracker.repo.tv;

import com.ruderu.arttracker.entity.tv.TvSeason;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TvSeasonsRepo extends CrudRepository<TvSeason, Long> {

    List<TvSeason> findAll();
    List<TvSeason> findAllByUsrId(Long usrId);
    List<TvSeason> findAllByStatus(String status);
    List<TvSeason> findAllByTvId(Long id);
}
