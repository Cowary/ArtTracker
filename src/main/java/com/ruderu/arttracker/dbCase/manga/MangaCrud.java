package com.ruderu.arttracker.dbCase.manga;

import com.ruderu.arttracker.dbCase.UserService;
import com.ruderu.arttracker.entity.manga.Manga;
import com.ruderu.arttracker.repo.manga.MangaRep;
import com.ruderu.arttracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MangaCrud {

    @Autowired
    MangaRep mangaRep;
    @Autowired
    UserService userService;

    public List<Manga> getAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return mangaRep.findAllByUsrId(userId);
        else return mangaRep.findAllByStatus(status);
    }

    public void save(Manga manga) {
        manga.setLastUpd(DateUtil.now());
        manga.setUsrId(userService.getIdCurrentUser());
        mangaRep.save(manga);
    }
}
