package com.ruderu.mediarecord.rest;

import com.ruderu.mediarecord.model.AnimeModel;
import com.ruderu.mediarecord.model.MangaModel;
import com.ruderu.mediarecord.model.RanobeModel;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShikimoriApi {

    static final String URL = "https://shikimori.one/api";
    static final String URL_SEARCH_ANIME = "/animes?limit=50&search=";
    static final String URL_ANIME = "/animes/";
    static final String URL_SEARCH_MANGA = "/mangas?limit=50&search=";
    static final String URL_MANGA = "/mangas/";
    static final String URL_SEARCH_RANOBE = "/ranobe?limit=50&search=";
    static final String URL_RANOBE = "/ranobe/";


    static RestTemplate restTemplate = new RestTemplate();

    public static List<AnimeModel> searchByName(String name) {
        AnimeModel[] models = restTemplate.getForObject(URL + URL_SEARCH_ANIME + name, AnimeModel[].class);
        // TODO: 23.01.2022 Обработать если null
        return new ArrayList<>(Arrays.asList(models));
    }

    public static AnimeModel findById(int animeId) {
        System.out.println(URL + URL_ANIME + animeId);
        AnimeModel model = restTemplate.getForObject(URL + URL_ANIME + animeId, AnimeModel.class);
        System.out.println(model);
        // TODO: 23.01.2022 Обработать если null
        return model;
    }

    public static List<MangaModel> searchMangaByName(String name) {
        MangaModel[] models = restTemplate.getForObject(URL + URL_SEARCH_MANGA + name, MangaModel[].class);
        // TODO: 23.01.2022 Обработать если null
        return new ArrayList<>(Arrays.asList(models));
    }

    public static MangaModel findMangaById(int id) {
        System.out.println(URL + URL_MANGA + id);
        MangaModel model = restTemplate.getForObject(URL + URL_MANGA + id, MangaModel.class);
        System.out.println(model);
        // TODO: 23.01.2022 Обработать если null
        return model;
    }

    public static List<RanobeModel> searchRanobeByName(String name) {
        RanobeModel[] models = restTemplate.getForObject(URL + URL_SEARCH_RANOBE + name, RanobeModel[].class);
        // TODO: 23.01.2022 Обработать если null
        return new ArrayList<>(Arrays.asList(models));
    }

    public static RanobeModel findRanobeById(int id) {
        System.out.println(URL + URL_RANOBE + id);
        RanobeModel model = restTemplate.getForObject(URL + URL_RANOBE + id, RanobeModel.class);
        System.out.println(model);
        // TODO: 23.01.2022 Обработать если null
        return model;
    }


}
