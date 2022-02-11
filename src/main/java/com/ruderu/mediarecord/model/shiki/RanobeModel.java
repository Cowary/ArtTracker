package com.ruderu.mediarecord.model.shiki;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RanobeModel {

    private int id;
    private String name;
    private String russian;
    private ImageModel image;
    private String url;
    private String kind;
    private String score;
    private String status;
    private int volumes;
    private int chapters;
    private String aired_on;
    private String released_on;
    private String rating;
    private String franchise;
    private Integer duration;
    private String[] publishers;

    private RoleModel[] roleModels;
}
