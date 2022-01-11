package com.ruderu.mediarecord.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString

@Entity(name = "anime")
public class Anime implements Media {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Integer count;
    @NonNull
    private String status;
    @NonNull
    private Integer score;
    @NonNull
    private String author;
    @NonNull
    private String studio;
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) // This is for bind Date with @ModelAttribute attern = "yyyy-MM-dd"  iso = DateTimeFormat.ISO.DATE
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Anime(@NonNull String name, @NonNull Integer count, @NonNull String status, @NonNull Integer score, @NonNull String author, @NonNull String studio, @NonNull  Date endDate) {
        this.name = name;
        this.count = count;
        this.status = status;
        this.score = score;
        this.author = author;
        this.studio = studio;
        this.endDate = endDate;
    }

    public Anime() {
    }
}
