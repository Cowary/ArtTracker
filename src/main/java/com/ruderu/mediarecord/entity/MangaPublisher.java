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
