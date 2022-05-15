package com.ruderu.arttracker.entity.movie;

import com.ruderu.arttracker.entity.Media;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString

@Entity(name = "movie")
public class Movie implements Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private Integer duration;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Transient
    private String type = "Movie";

    public Movie(String originalTitle, String title, Date releaseDate, Integer duration) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.status = "Ready to Start";
    }

    public Movie(String originalTitle, String title, Date releaseDate, Integer duration, String status, Integer score, Date endDate) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.status = status;
        this.score = score;
        this.endDate = endDate;
    }

    public Movie() {

    }
}
