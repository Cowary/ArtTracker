package com.ruderu.arttracker.rest.api.kin;

import com.ruderu.arttracker.rest.model.kin.KinResultModel;
import com.ruderu.arttracker.rest.model.kin.KinSeasonsModel;
import com.ruderu.arttracker.rest.util.ApiUrl;

import java.net.URL;
import java.util.List;

public class SerialApi extends FilmApi {

    public List<KinResultModel> searchByKeyword(String keyword) {
        return super.searchByKeyword(keyword, List.of("TV_SERIES", "MINI_SERIES"));
    }

    public int totalSeasons(int id) {
        URL urlTotalSeasons = new ApiUrl("kin.properties")
                .appendPathFromFile("URL_FILM")
                .appendPath(id)
                .appendPathFromFile("URL_SEASONS")
                .build();

    return restTemp.getBody(urlTotalSeasons, request, KinSeasonsModel.class)
            .getTotal();
    }
}
