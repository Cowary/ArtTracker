package com.ruderu.arttracker.entity.manga;

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

@Entity(name = "manga")
public class Manga extends Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTitle;
    private String title;
    private Integer volumes;
    private Integer chapters;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private Integer releaseYear;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Long shikiId;
    private Integer volumesEnd;
    private Integer chaptersEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date lastUpd;
    private Long usrId;
    @Transient
    private String type = "Manga";

    public Manga(String originalTitle, String title, Integer volumes, Integer chapters, Date releaseDate, Long shikiId) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.volumes = volumes;
        this.chapters = chapters;
        this.releaseDate = releaseDate;
        this.releaseYear = DateUtil.getYear(releaseDate);
        this.shikiId = shikiId;
        this.status = "Planned";
    }

    public Manga() {

    }
}
