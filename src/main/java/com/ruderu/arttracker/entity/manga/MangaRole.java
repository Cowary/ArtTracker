package com.ruderu.arttracker.entity.manga;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
