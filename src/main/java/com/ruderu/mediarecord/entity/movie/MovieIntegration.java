package com.ruderu.mediarecord.entity.movie;

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

@Entity(name = "movie_integration")
public class MovieIntegration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long idMovie;
    private Long idIntegration;

    public MovieIntegration(String name, Long idMovie, Long idIntegration) {
        this.name = name;
        this.idMovie = idMovie;
        this.idIntegration = idIntegration;
    }

    public MovieIntegration() {
    }
}
