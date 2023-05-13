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

@Entity(name = "manga_publisher")
public class MangaPublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long mangaId;
    private Long publisherId;

    public MangaPublisher(Long mangaId, Long publisherId) {
        this.mangaId = mangaId;
        this.publisherId = publisherId;
    }

    public MangaPublisher() {
    }
}
