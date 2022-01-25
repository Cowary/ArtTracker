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

@Entity(name = "anime")
public class Anime implements Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private Integer episodes;
    private String status;
    private Double score;
    private String author;
    private String studio;
    @DateTimeFormat(pattern = "yyyy-mm-dd") // This is for bind Date with @ModelAttribute attern = "yyyy-MM-dd"  iso = DateTimeFormat.ISO.DATE
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date airedOn;

    public Anime(String nameEn, String nameRu, Integer episodes, String status, Double score, String author, String studio, Date endDate, Date airedOn) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.episodes = episodes;
        this.status = status;
        this.score = score;
        this.author = author;
        this.studio = studio;
        this.endDate = endDate;
        this.airedOn = airedOn;
    }

    public Anime(String nameEn, String nameRu, Integer episodes, Date airedOn) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.episodes = episodes;
        this.status = "Ready to Start";
        this.score = 0.0;
        this.airedOn = airedOn;
//        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            this.endDate = formatter2.parse("2000-01-01");
//            System.out.println("EndDate = " + endDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    public Anime(String nameEn, String nameRu, Integer episodes, String status, Double score, Date airedOn) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.episodes = episodes;
        this.status = status;
        this.score = score;
        this.airedOn = airedOn;
    }

    public Anime(String nameEn) {
        this.nameEn = nameEn;
    }

    public Anime() {
    }
}
