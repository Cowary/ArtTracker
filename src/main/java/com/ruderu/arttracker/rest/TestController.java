package com.ruderu.arttracker.rest;

import com.ruderu.arttracker.dbCase.anime.AnimeCrud;
import com.ruderu.arttracker.entity.anime.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    AnimeCrud animeCrud;

    @RequestMapping("/heh")
    public Anime getById() {
        return animeCrud.getById(25);
    }
}
