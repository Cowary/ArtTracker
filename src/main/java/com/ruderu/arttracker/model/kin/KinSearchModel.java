package com.ruderu.arttracker.model.kin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KinSearchModel {

    private String keyword;
    private int pagesCount;
    private int searchFilmsCountResult;
    @JsonProperty("films")
    private KinResultModel[] kinResultModels;


    public KinSearchModel() {
    }
}
