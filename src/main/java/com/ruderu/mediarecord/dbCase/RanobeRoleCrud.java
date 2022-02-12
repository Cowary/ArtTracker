package com.ruderu.mediarecord.dbCase;

import com.ruderu.mediarecord.entity.Person;
import com.ruderu.mediarecord.entity.RanobeRole;
import com.ruderu.mediarecord.model.shiki.RoleModel;
import com.ruderu.mediarecord.repo.RanobeRoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RanobeRoleCrud {

    @Autowired
    RanobeRoleRep ranobeRoleRep;

    @Autowired
    PersonCrud personCrud;

    public List<RanobeRole> getByAnimeId(int ranobeId) {
        return ranobeRoleRep.findByRanobeId(ranobeId);
    }

    public void createRanobeRole(long ranobeId, RoleModel roleModel) {
        Person person = personCrud.createOrGetByName(roleModel.getPersonModel());
        for (int i = 0; i < roleModel.getRoles().length; i++) {
            RanobeRole ranobeRole = new RanobeRole(roleModel.getRoles()[i], roleModel.getRolesRussian()[i], ranobeId, person.getId());
            ranobeRoleRep.save(ranobeRole);
        }
    }

    public void createRanobeRole(long ranobeId, List<RoleModel> roleModels) {
        List<RanobeRole> ranobeRoleList = new ArrayList<>();
        for (RoleModel roleModel : roleModels) {
            if(roleModel.getPersonModel() != null) {
                Person person = personCrud.createOrGetByName(roleModel.getPersonModel());
                for (int i = 0; i < roleModel.getRoles().length; i++) {
                    RanobeRole ranobeRole = new RanobeRole(roleModel.getRoles()[i], roleModel.getRolesRussian()[i], ranobeId, person.getId());
                    ranobeRoleList.add(ranobeRole);
                }
            }
        }
        ranobeRoleRep.saveAll(ranobeRoleList);

    }

}
