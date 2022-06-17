package com.ruderu.arttracker.dbCase.anime;

import com.ruderu.arttracker.dbCase.UserService;
import com.ruderu.arttracker.entity.anime.Anime;
import com.ruderu.arttracker.repo.AnimeRepo;
import com.ruderu.arttracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimeCrud {

    @Autowired
    AnimeRepo animeRepo;
    @Autowired
    UserService userService;

    public List<Anime> getAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return animeRepo.findAllByUsrId(userId);
        else return animeRepo.findByStatusAndUsrId(status, userId);
    }

    public void save(Anime anime) {
        anime.setLastUpd(DateUtil.now());
        animeRepo.save(anime);
    }
}
