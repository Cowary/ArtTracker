package com.ruderu.arttracker.repo.user;

import com.ruderu.arttracker.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
}
