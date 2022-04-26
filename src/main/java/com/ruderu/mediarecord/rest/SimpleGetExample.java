package com.ruderu.mediarecord.rest;

public class SimpleGetExample {

    static final String URL_EXAMPLE = "https://shikimori.one/api/animes?limit=10&search='Кровь'";

    public static void main(String[] args) {

        //TmdbApi.searchMovieById(557);
        //TmdbApi.searchFilm("Spider-man");

        System.out.println("kek");

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
