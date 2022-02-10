package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.AnimeRole;
import com.ruderu.mediarecord.entity.Person;
import com.ruderu.mediarecord.model.shiki.RoleModel;
import com.ruderu.mediarecord.repo.AnimeRoleRep;
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
