package com.ruderu.mediarecord.entity.manga;

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

@Entity(name = "manga_role")
public class MangaRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private Long mangaId;
    private Long personId;

    public MangaRole(String nameEn, String nameRu, Long mangaId, Long personId) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.mangaId = mangaId;
        this.personId = personId;
    }

    public MangaRole() {
    }
}
