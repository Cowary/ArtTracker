package com.ruderu.arttracker.api;

import com.ruderu.arttracker.rest.api.ShikimoriApi;
import com.ruderu.arttracker.rest.model.shiki.AnimeModel;
import com.ruderu.arttracker.util.Assertions;
import com.ruderu.arttracker.util.ProperUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ShikiTest {

    private static String URL;
    private static String URL_ANIME;
    private static String URL_MANGA;
    private static String URL_RANOBE;


    @BeforeAll
    static void prepare() {
        ProperUtil properUtil = new ProperUtil();
        URL = properUtil.getProp("shiki.URL");
        URL_ANIME = properUtil.getProp("shiki.URL_ANIME");
        URL_MANGA = properUtil.getProp("shiki.URL_MANGA");
        URL_RANOBE = properUtil.getProp("shiki.URL_RANOBE");
    }

    @Test
    public void getAnimeTest() {
        int animeId = 43608;

        RestAssured
                .when().get(URL + URL_ANIME + animeId)
                .then().assertThat().statusCode(200);

        AnimeModel animeModel = ShikimoriApi.getById(43608);
        new Assertions()
                .equals("ID", 43608, animeModel.getId())
                .equals("Название на русском", "Госпожа Кагуя: в любви как на войне 3", animeModel.getRussian())
                .equals("Название", "Kaguya-sama wa Kokurasetai: Ultra Romantic", animeModel.getName())
                .notNull("Картинка", animeModel.getImage())
                .notNull("Студии", animeModel.getStudios())
                .equals("URL", "/animes/43608-kaguya-sama-wa-kokurasetai-ultra-romantic", animeModel.getUrl())
                .equals("", "tv", animeModel.getKind())
                .equals("Статус", "released", animeModel.getStatus())
                .equals("Количество эпизодов", 13, animeModel.getEpisodes())
                .equals("Количество вышедших эпизодов", 13, animeModel.getEpisodes_aired())
                .equals("Дата вешения", "2022-04-09", animeModel.getAired_on())
                .equals("Дата выхода", "2022-06-25", animeModel.getReleased_on())
                .equals("Рейтинг", "pg_13", animeModel.getRating())
                .equals("Франшиза", "kaguya_sama", animeModel.getFranchise())
                .equals("Длительной", 23, animeModel.getDuration())
                .notNull("Роли", animeModel.getRoleModels());
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMangaTest() {
        int mangaId = 25;

        RestAssured
                .when().get(URL + URL_MANGA + mangaId)
                .then().statusCode(200);
    }

    @Test
    public void getRanobeTest() {
        int ranobeId = 9115;

        RestAssured
                .when().get(URL + URL_RANOBE + ranobeId)
                .then().statusCode(200);
    }

    @Test
    public void apiTest() {
        ProperUtil properUtil = new ProperUtil();
        String URL = properUtil.getProp("shiki.URL");

        RestAssured
                .when().get(URL)
                .then().assertThat().statusCode(200);
    }
}
