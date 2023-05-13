package com.ruderu.arttracker.entity.anime;

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

@Entity(name = "anime_role")
public class AnimeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private Long animeId;
    private Long personId;

    public AnimeRole(String nameEn, String nameRu, Long animeId, Long personId) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.animeId = animeId;
        this.personId = personId;
    }

    public AnimeRole() {
    }
}
