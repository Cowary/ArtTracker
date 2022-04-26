package com.ruderu.mediarecord.dbCase.manga;

import com.ruderu.mediarecord.dbCase.PersonCrud;
import com.ruderu.mediarecord.entity.Person;
import com.ruderu.mediarecord.entity.manga.MangaRole;
import com.ruderu.mediarecord.model.shiki.RoleModel;
import com.ruderu.mediarecord.repo.MangaRoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MangaRoleCrud {

    @Autowired
    MangaRoleRep mangaRoleRep;
    @Autowired
    PersonCrud personCrud;

    public List<MangaRole> getByAnimeId(int mangaId) {
        return mangaRoleRep.findByMangaId(mangaId);
    }

    public void create(long mangaId, RoleModel roleModel) {
        Person person = personCrud.createOrGetByName(roleModel.getPersonModel());
        for (int i = 0; i < roleModel.getRoles().length; i++) {
            MangaRole ranobeRole = new MangaRole(roleModel.getRoles()[i], roleModel.getRolesRussian()[i], mangaId, person.getId());
            mangaRoleRep.save(ranobeRole);
        }
    }

    public void create(long mangaId, List<RoleModel> roleModels) {
        List<MangaRole> mangaRoles = new ArrayList<>();
        for (RoleModel roleModel : roleModels) {
            if(roleModel.getPersonModel() != null) {
                Person person = personCrud.createOrGetByName(roleModel.getPersonModel());
                for (int i = 0; i < roleModel.getRoles().length; i++) {
                    MangaRole mangaRole = new MangaRole(roleModel.getRoles()[i], roleModel.getRolesRussian()[i], mangaId, person.getId());
                    mangaRoles.add(mangaRole);
                }
            }
            mangaRoleRep.saveAll(mangaRoles);
        }
    }

}
