package com.ruderu.mediarecord.model.shiki;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleModel {
    private String[] roles;
    @JsonProperty("roles_russian")
    private String[] rolesRussian;
    private PersonModel personModel;

}
