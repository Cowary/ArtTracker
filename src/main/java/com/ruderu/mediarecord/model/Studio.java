package com.ruderu.mediarecord.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Studio {
    private int id;
    private String name;
    private String filtered_name;
    private boolean real;
    private String image;

    public Studio() {
    }
}


