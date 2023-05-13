package com.ruderu.arttracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity(name = "studio")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String filteredName;
    private String image;

    public Studio(String name, String filteredName, String image) {
        this.name = name;
        this.filteredName = filteredName;
        this.image = image;
    }

    public Studio(String name) {
        this.name = name;
    }

    public Studio() {
    }

}
