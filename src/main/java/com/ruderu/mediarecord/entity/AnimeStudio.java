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

@Entity(name = "anime_studio")
public class AnimeStudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String filteredName;
    private String image;
    private Long animeId;

    public AnimeStudio(String name, String filteredName, String image, Long animeId) {
        this.name = name;
        this.filteredName = filteredName;
        this.image = image;
        this.animeId = animeId;
    }

    public AnimeStudio() {
    }

}
