package com.ruderu.mediarecord.entity.anime;

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

@Entity(name = "anime_studio")
public class AnimeStudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long animeId;
    private Long studioId;


    public AnimeStudio(Long animeId, Long studioId) {
        this.animeId = animeId;
        this.studioId = studioId;
    }

    public AnimeStudio() {
    }

}
