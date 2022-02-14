package com.ruderu.mediarecord.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString

@Entity(name = "manga")
public class Manga implements Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private Integer volumes;
    private Integer chapters;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date airedOn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releasedOn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String ongoingStart;
    private Long shikiId;
    private String comment;
    private Integer volumesEnd;
    private Integer chaptersEnd;
    @Transient
    private String type = "Manga";

    public Manga(String nameEn, String nameRu, Integer volumes, Integer chapters, Date airedOn, Long shikiId) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.volumes = volumes;
        this.chapters = chapters;
        this.airedOn = airedOn;
        this.shikiId = shikiId;
        this.status = "Ready to Start";
        this.score = 0;
    }

    public Manga() {

    }
}
