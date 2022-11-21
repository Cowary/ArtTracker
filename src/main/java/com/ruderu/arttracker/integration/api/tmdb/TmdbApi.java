package com.ruderu.arttracker.integration.api.tmdb;


import com.ruderu.arttracker.integration.model.tmdb.MovieModel;
import com.ruderu.arttracker.integration.model.tmdb.ResultModel;
import com.ruderu.arttracker.integration.model.tmdb.SearchModel;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TmdbApi {

    static private final String URL = "https://api.themoviedb.org/3";
    static private final String KEY = "api_key=fd2a3fa0257d86765ec94bbd2ed116ba";

    static RestTemplate restTemplate = new RestTemplate();

    public static List<ResultModel> searchFilm(String query) {
        SearchModel searchModel = restTemplate.getForObject(URL +"/search/movie?"+ KEY +"&query="+ query + "&language=ru-RU&page=1", SearchModel.class);
        Assert.isTrue(searchModel != null, "Error TmdbApi");
        List<ResultModel> list = new ArrayList<>(Arrays.asList(searchModel.getResults()));
        for(int i = 2; i <= searchModel.getTotalPages(); i++ ) {
            SearchModel searchModel2 = restTemplate.getForObject(URL +"/search/movie?"+ KEY +"&query="+ query + "&language=ru-RU&page=" + i, SearchModel.class);
            if (searchModel2 != null) {
                list.addAll(Arrays.asList(searchModel2.getResults()));
            }
        }
        for (ResultModel s: list) {
            System.out.println(s);
        }
        return list;
    }

    public static MovieModel searchMovieById(int id) {
        MovieModel movieModel = restTemplate.getForObject(URL + "/movie/" + id + "?" + KEY + "&language=ru-RU", MovieModel.class);
        Assert.isTrue(movieModel != null, "Error TmdbApi");
        System.out.println(movieModel);
        return movieModel;
    }
}
