package com.ruderu.arttracker.entity.ranobe;

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

@Entity(name = "ranobe_volume")
public class RanobeVolume implements Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer chapters;
    private Integer chaptersEnd;
    private String status;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date lastUpd;
    private Long ranobeId;
    @Transient
    private String originalTitle;
    @Transient
    private Integer volumes;
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Transient
    private Integer releaseYear;
    @Transient
    private String ongoingStart;
    @Transient
    private Integer volumesEnd;
    @Transient
    private static final String type = "Ranobe";

    public void setCommonField(Ranobe ranobe) {
        originalTitle = ranobe.getOriginalTitle();
        volumes = ranobe.getVolumes();
        releaseDate = ranobe.getReleaseDate();
        ongoingStart = ranobe.getOngoingStart();
        volumesEnd = ranobe.getVolumesEnd();
    }
}
