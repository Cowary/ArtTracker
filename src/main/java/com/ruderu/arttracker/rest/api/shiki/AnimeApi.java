package com.ruderu.arttracker.rest.api.shiki;

import com.ruderu.arttracker.rest.model.shiki.AnimeModel;

import java.util.List;

public class AnimeApi extends TitleApi {

    public List<AnimeModel> searchByName(String keyword) {
        return List.of(super.searchByName(keyword, "URL_ANIME", AnimeModel[].class));
    }

    public AnimeModel getById(int id) {
        AnimeModel model = super.getById(id, "URL_ANIME", AnimeModel.class);
        model.setRoleModels(super.getRoleById(id, "URL_ANIME"));

        return model;
    }
}
