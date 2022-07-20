package com.ruderu.arttracker.rest.api;

import com.ruderu.arttracker.rest.model.shiki.AnimeModel;
import com.ruderu.arttracker.rest.model.shiki.MangaModel;
import com.ruderu.arttracker.rest.model.shiki.RanobeModel;
import com.ruderu.arttracker.rest.model.shiki.RoleModel;
import com.ruderu.arttracker.util.ProperUtil;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShikimoriApi {

    static final String URL;
    static final String URL_SEARCH_ANIME = "/animes?limit=50&search=";
    static final String URL_ANIME = "/animes/";
    static final String URL_SEARCH_MANGA = "/mangas?limit=50&search=";
    static final String URL_MANGA = "/mangas/";
    static final String URL_SEARCH_RANOBE = "/ranobe?limit=50&search=";
    static final String URL_RANOBE = "/ranobe/";
    static final String URL_ROLES =  "/roles";

    static {
        ProperUtil properUtil = new ProperUtil();
        URL = properUtil.getProp("shiki.URL");
    }

    static RestTemplate restTemplate = new RestTemplate();

    public static List<AnimeModel> searchByName(String name) {
        AnimeModel[] models = restTemplate.getForObject(URL + URL_SEARCH_ANIME + name, AnimeModel[].class);
        if (models != null) {
            return new ArrayList<>(Arrays.asList(models));
        } else {
            throw new IllegalStateException("Ничего не найдено");
        }
    }

    public static AnimeModel findById(int animeId) {
        AnimeModel model = restTemplate.getForObject(URL + URL_ANIME + animeId, AnimeModel.class);
        RoleModel[] roleModels = restTemplate.getForObject(URL + URL_ANIME + animeId + URL_ROLES, RoleModel[].class);
        if(model == null) {
            throw new IllegalStateException("Ничего не найдено");
        }
        model.setRoleModels(roleModels);

        return model;
    }

    public static List<MangaModel> searchMangaByName(String name) {
        MangaModel[] models = restTemplate.getForObject(URL + URL_SEARCH_MANGA + name, MangaModel[].class);
        if (models != null) {
            return new ArrayList<>(Arrays.asList(models));
        } else {
            throw new IllegalStateException("Ничего не найдено" );
        }
    }

    public static MangaModel findMangaById(int id) {
        System.out.println(URL + URL_MANGA + id);
        MangaModel model = restTemplate.getForObject(URL + URL_MANGA + id, MangaModel.class);
        RoleModel[] roleModels = restTemplate.getForObject(URL + URL_MANGA + id + URL_ROLES, RoleModel[].class);
        if(model == null) {
            throw new IllegalStateException("Ничего не найдено");
        }
        model.setRoleModels(roleModels);

        return model;
    }

    public static List<RanobeModel> searchRanobeByName(String name) {
        RanobeModel[] models = restTemplate.getForObject(URL + URL_SEARCH_RANOBE + name, RanobeModel[].class);
        if (models != null) {
            return new ArrayList<>(Arrays.asList(models));
        } else {
            throw new IllegalStateException("Ничего не найдено");
        }
    }

    public static RanobeModel findRanobeById(int id) {
        System.out.println(URL + URL_RANOBE + id);
        RanobeModel model = restTemplate.getForObject(URL + URL_RANOBE + id, RanobeModel.class);
        RoleModel[] roleModels = restTemplate.getForObject(URL + URL_RANOBE + id + URL_ROLES, RoleModel[].class);
        if (model == null) {
            throw new IllegalStateException("Ничего не найдено");
        }
        model.setRoleModels(roleModels);

        return model;
    }


}
