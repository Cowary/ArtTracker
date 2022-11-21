package com.ruderu.arttracker.api.shiki;

import com.ruderu.arttracker.integration.api.shiki.ShikimoriApi;
import com.ruderu.arttracker.integration.model.shiki.AnimeModel;
import com.ruderu.arttracker.integration.model.shiki.MangaModel;
import com.ruderu.arttracker.integration.model.shiki.RanobeModel;
import com.ruderu.arttracker.util.Assertions;
import com.ruderu.arttracker.util.Wait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ShikiTest {

    @BeforeEach
    public void before() {
        Wait.seconds(1);
    }

    @Test
    public void getAnimeTest() {
        int animeId = 43608;

        AnimeModel animeModel = ShikimoriApi.animeApi().getById(animeId);
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
                .notNull("Роли", animeModel.getRoleModels())
                .condition("Роли больше одной", animeModel.getRoleModels(), m -> Arrays.stream(m).count() > 1);
    }

    @Test
    public void getMangaTest() {
        int mangaId = 25;

        MangaModel model = ShikimoriApi.mangaApi().getById(mangaId);
        new Assertions()
                .notNull("Модель", model);
    }

    @Test
    public void getRanobeTest() {
        int ranobeId = 9115;

        RanobeModel model = ShikimoriApi.ranobeApi().getById(ranobeId);
        new Assertions()
                .notNull("Модель", model);
    }

    @Test
    public void searchAnimeTest() {
        List<AnimeModel> models = ShikimoriApi.animeApi()
                .searchByName("Стальной алхимик");
        new Assertions()
                .notNull("Модели", models)
                .isTrue("AnimeModel не пустой", models.size() > 0);
    }

    @Test
    public void searchMangaTest() {
        List<MangaModel> models = ShikimoriApi.mangaApi().searchByName("Стальной алхимик");
        new Assertions()
                .notNull("Модели", models)
                .isTrue("MangaModel не пустой", models.size() > 0);
    }

    @Test
    public void searchRanobeTest() {
        List<RanobeModel> models = ShikimoriApi.ranobeApi().searchByName("Стальной алхимик");
        new Assertions()
                .notNull("Модели", models)
                .isTrue("RanobeModel не пустой", models.size() > 0);
    }
}
