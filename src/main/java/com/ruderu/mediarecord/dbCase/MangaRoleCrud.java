package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.MangaRole;
import com.ruderu.mediarecord.repo.MangaRoleRep;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MangaRoleCrud {

    @Autowired
    MangaRoleRep mangaRoleRep;

    public List<MangaRole> getByAnimeId(int mangaId) {
        return mangaRoleRep.findByMangaId(mangaId);
    }

}
