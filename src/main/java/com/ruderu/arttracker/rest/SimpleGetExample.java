package com.ruderu.arttracker.rest;

import com.ruderu.arttracker.model.kin.KinResultModel;

import java.util.List;

public class SimpleGetExample {

    public static void main(String[] args) {

        //TmdbApi.searchMovieById(557);
        //TmdbApi.searchFilm("Spider-man");
        //KinApi.searchByName(301);

        List<KinResultModel> list = KinApi.searchByKeyword("Матрица", "FILM");

        ShikimoriApi.searchByName("харухи");
        System.out.println(list);

//        RestTemplate restTemplate = new RestTemplate();
//        AnimeModel[] models = restTemplate.getForObject(URL_EXAMPLE, AnimeModel[].class);
//        System.out.println(models[0]);
//        if (models != null) {
//            for (AnimeModel model : models) {
//                System.out.println(model.getId() + " " + model.getName());
//                System.out.println(model);
//                //FileUtil.downloadFile("https://dere.shikimori.one" + model.getImage().getOriginal(), model.getName());
//            }
//        }
        //Assert.notEmpty(models, "Отсуствуют модели");
        //Assert.notNull(models[0].getId(), "Id не найден");


    }
}
