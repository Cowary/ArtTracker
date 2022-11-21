package com.ruderu.arttracker.api.kin;

import com.ruderu.arttracker.integration.api.kin.KinApi;
import com.ruderu.arttracker.integration.model.kin.KinFilmModel;
import com.ruderu.arttracker.integration.model.kin.KinResultModel;
import com.ruderu.arttracker.util.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KinTest {

    @Test
    public void getByIdTest() {
        KinFilmModel kinFilmModel = KinApi.filmApi().getById(300);
        new Assertions()
                .notNull("kinFilModel не пустой", kinFilmModel)
                .isTrue("Сериал Лиззи Магуайр найден",  kinFilmModel.getNameRu().equals("Лиззи Магуайр")
                );
    }

    @Test
    public void searchFilmTest() {
        List<KinResultModel> kinFilmModelList = KinApi.filmApi().searchByKeyword("Матрица", List.of("FILM"));
        new Assertions().isTrue("kinFilmModelList не пустой", kinFilmModelList.size() > 0);
    }

    @Test
    public void searchSerial() {
        List<KinResultModel> kinResultModelList = KinApi.serialApi().searchByKeyword("Ms. Marvel");
        new Assertions().isTrue("kinFilmModelList не пустой", kinResultModelList.size() > 0);
        KinResultModel kinResultModel = kinResultModelList.get(0);
        new Assertions().isTrue("Сериал Мисс Марвел найден",  kinResultModel.getNameRu().equals("Мисс Марвел"));

        int totalSeason = KinApi.serialApi().totalSeasons(kinResultModel.getFilmId());
        new Assertions().equals("Количество сезонов совпадает", 1, totalSeason);
    }
}
