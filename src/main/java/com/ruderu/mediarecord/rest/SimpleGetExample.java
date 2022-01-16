package com.ruderu.mediarecord.rest;

import com.ruderu.mediarecord.model.AnimeModel;
import org.springframework.web.client.RestTemplate;

public class SimpleGetExample {

    static final String URL_EXAMPLE = "https://shikimori.one/api/animes?season=2016,2015&limit=100";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        AnimeModel[] models = restTemplate.getForObject(URL_EXAMPLE, AnimeModel[].class);
        if (models != null) {
            for (AnimeModel model : models) {
                System.out.println(model.getId() + " " + model.getName());
                System.out.println(model);
            }
        }


    }
}
