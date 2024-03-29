package com.ruderu.arttracker.entity.anime;

import com.ruderu.arttracker.entity.Media;
import com.ruderu.arttracker.util.DateUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString

@Entity(name = "anime")
public class Anime extends Media {

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
    private Date endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private Integer releaseYear;
    private Long shikiId;
    private Integer duration;
    private Integer episodesEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date lastUpd;
    private Long usrId;
    @Transient
    private String type = "Anime";

    public Anime(String originalTitle, String title, Integer episodes, Date releaseDate, Long shikiId, Integer duration) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.episodes = episodes;
        this.releaseDate = releaseDate;
        this.releaseYear = DateUtil.getYear(releaseDate);
        this.shikiId = shikiId;
        this.duration = duration;
    }

    public Anime(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Anime() {
    }
}
