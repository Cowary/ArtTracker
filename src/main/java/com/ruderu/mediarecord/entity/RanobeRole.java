package com.ruderu.mediarecord.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString

@Entity(name = "ranobe_role")
public class RanobeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private Long ranobeId;
    private Long personId;

    public RanobeRole(String nameEn, String nameRu, Long ranobeId, Long personId) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.ranobeId = ranobeId;
        this.personId = personId;
    }

    public RanobeRole() {
    }
}
