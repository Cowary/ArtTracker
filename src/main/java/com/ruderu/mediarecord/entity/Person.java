package com.ruderu.mediarecord.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString

@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEn;
    private String nameRu;
    private int shikiId;

    public Person(String nameEn, String nameRu, int shikiId) {
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.shikiId = shikiId;
    }

    public Person() {
    }
}