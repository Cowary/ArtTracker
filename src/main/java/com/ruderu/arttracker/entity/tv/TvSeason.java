package com.ruderu.arttracker.entity.tv;

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

@Entity(name = "tv_seasons")
public class TvSeason extends Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer episodes;
    private Integer episodesEnd;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date lastUpd;
    private Long tvId;

    @Transient
    private String originalTitle;
    @Transient
    private Integer seasons;
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Transient
    private Integer releaseYear;
    @Transient
    private Integer seasonsEnd;
    @Transient
    private static final String type = "Tv";

    public void setCommonField(Tv tv) {
        originalTitle = tv.getOriginalTitle();
        seasons = tv.getSeasons();
        releaseDate = tv.getReleaseDate();
        seasonsEnd = tv.getSeasonsEnd();
    }
}
