package com.ruderu.arttracker.model.kin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KinResultModel {

    private int filmId;
    private String nameRu;
    private String nameEn;
    private String type;
    private String year;
    private Double rating;
}