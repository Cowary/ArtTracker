package com.ruderu.mediarecord.entity.anime;

import com.ruderu.mediarecord.entity.Media;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString

@Entity(name = "anime")
public class Anime implements Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    private String title;
    private Integer episodes;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private Long shikiId;
    private Integer duration;
    private String ongoingStart;
    private String comment;
    private Integer episodesEnd;
    @Transient
    private String type = "Anime";

    public Anime(String originalTitle, String title, Integer episodes, String status, Integer score, Date endDate, Date releaseDate) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.episodes = episodes;
        this.status = status;
        this.score = score;
        this.endDate = endDate;
        this.releaseDate = releaseDate;
    }

    public Anime(String originalTitle, String title, Integer episodes, Date releaseDate, Long shikiId) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.episodes = episodes;
        this.status = "Ready to Start";
        this.score = 0;
        this.releaseDate = releaseDate;
        this.shikiId = shikiId;
        this.comment = "";
        this.episodesEnd = 0;
    }

    public Anime(String originalTitle, String title, Integer episodes, String status, Integer score, Date releaseDate) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.episodes = episodes;
        this.status = status;
        this.score = score;
        this.releaseDate = releaseDate;
    }

    public Anime(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Anime() {
    }
}
