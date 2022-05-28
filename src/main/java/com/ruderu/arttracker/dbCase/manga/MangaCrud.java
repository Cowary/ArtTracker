package com.ruderu.arttracker.dbCase.manga;

import com.ruderu.arttracker.entity.manga.Manga;
import com.ruderu.arttracker.repo.MangaRep;
import com.ruderu.arttracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MangaCrud {

    @Autowired
    MangaRep mangaRep;

    public List<Manga> getAll(String status) {
        if(status.equals("")) return mangaRep.findAll();
        else return mangaRep.findAllByStatus(status);
    }

    public void save(Manga manga) {
        manga.setLastUpd(DateUtil.now());
        mangaRep.save(manga);
    }
}
