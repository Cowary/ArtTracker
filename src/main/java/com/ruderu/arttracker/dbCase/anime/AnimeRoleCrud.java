package com.ruderu.arttracker.dbCase.anime;

import com.ruderu.arttracker.dbCase.PersonCrud;
import com.ruderu.arttracker.entity.Person;
import com.ruderu.arttracker.entity.anime.AnimeRole;
import com.ruderu.arttracker.model.shiki.RoleModel;
import com.ruderu.arttracker.repo.AnimeRoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeRoleCrud {

    @Autowired
    AnimeRoleRep animeRoleRep;
    @Autowired
    PersonCrud personCrud;

    public List<AnimeRole> getByAnimeId(int animeId) {
        return animeRoleRep.findByAnimeId(animeId);
    }

    public void create(long animeId, RoleModel[] roleModels) {
        List<AnimeRole> animeRoleList = new ArrayList<>();
        for (RoleModel roleModel : roleModels) {
            if(roleModel.getPersonModel() != null) {
                Person person = personCrud.createOrGetByName(roleModel.getPersonModel());
                for (int i = 0; i < roleModel.getRoles().length; i++) {
                    animeRoleList.add(new AnimeRole(roleModel.getRoles()[i], roleModel.getRolesRussian()[i], animeId, person.getId()));
                }
            }
        }
        animeRoleRep.saveAll(animeRoleList);
    }
}
