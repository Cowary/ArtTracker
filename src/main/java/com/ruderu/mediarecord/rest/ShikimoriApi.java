package com.ruderu.mediarecord.rest;

import com.ruderu.mediarecord.model.AnimeModel;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShikimoriApi {

    static final String URL = "https://shikimori.one/api";
    static final String URL_SEARCH = "/animes?limit=50&search=";
    static final String URL_ANIME = "/animes/";

    static RestTemplate restTemplate = new RestTemplate();

    public static List<AnimeModel> searchByName(String name) {
        AnimeModel[] models = restTemplate.getForObject(URL + URL_SEARCH + name, AnimeModel[].class);
        // TODO: 23.01.2022 Обработать если null
        return new ArrayList<>(Arrays.asList(models));
    }

    public static AnimeModel findById(int animeId) {
        System.out.println(URL + URL_ANIME + animeId);
        AnimeModel model = restTemplate.getForObject(URL + URL_ANIME + animeId, AnimeModel.class);
        // TODO: 23.01.2022 Обработать если null
        return model;
    }


}
