package com.ruderu.arttracker.rest.api;

import com.ruderu.arttracker.rest.model.kin.KinFilmModel;
import com.ruderu.arttracker.rest.model.kin.KinResultModel;
import com.ruderu.arttracker.rest.model.kin.KinSearchModel;
import com.ruderu.arttracker.rest.model.kin.KinSeasonsModel;
import com.ruderu.arttracker.util.ProperUtil;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KinApi {

    private static final String URL;
    private static final String URL_FILM;
    private static final String URL_SEARCH;
    private static final String URL_SEASONS;

    private final static RestTemplate restTemplate = new RestTemplate();
    private final static HttpHeaders headers = new HttpHeaders();
    private final static  HttpEntity<String> request;

    static {
        ProperUtil properUtil = new ProperUtil();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(
                "X-API-KEY",
                properUtil.getProp("kin.api-key")
        );
        request = new HttpEntity<>(headers);

        URL = properUtil.getProp("kin.URL");
        URL_FILM = properUtil.getProp("kin.URL_FILM");
        URL_SEARCH = properUtil.getProp("kin.URL_SEARCH");
        URL_SEASONS = properUtil.getProp("kin.URL_SEASONS");
    }

    public static KinFilmModel searchById(int id) {
        ResponseEntity<KinFilmModel> response = restTemplate.exchange(
                URL + URL_FILM + id,
                HttpMethod.GET,
                request,
                KinFilmModel.class,
                1
        );
        KinFilmModel kinFilmModel = response.getBody();
        if (kinFilmModel == null) {
            throw new IllegalStateException("Ничего не найдено");
        }

        return kinFilmModel;
    }

    public static List<KinResultModel> searchByKeyword(String keyword, String type) {
        ResponseEntity<KinSearchModel> response = restTemplate.exchange(
                URL + URL_SEARCH + keyword + "&page=" + 1,
                HttpMethod.GET,
                request,
                KinSearchModel.class,
                1
        );

        List<KinResultModel> resultModels = new ArrayList<>();
        KinSearchModel kinSearchModel = response.getBody();
        if (kinSearchModel == null) {
            throw new IllegalStateException("Ничего не найдено");
        }
        int pageCount = Math.min(kinSearchModel.getPagesCount(), 5);
        for (int i = 1; i <= pageCount; i++) {
            resultModels.addAll(searchByKeyword(keyword, i));
        }
        return resultModels
                .stream()
                .filter(res -> res.getType().equals(type))
                .collect(Collectors.toList());
    }

    public static int totalSeasons(int id) {
        ResponseEntity<KinSeasonsModel> response = restTemplate.exchange(
                URL + URL_FILM + id + URL_SEASONS,
                HttpMethod.GET,
                request,
                KinSeasonsModel.class,
                1
        );
        KinSeasonsModel kinSeasonsModel = response.getBody();
        if (kinSeasonsModel == null) {
            throw new IllegalStateException("Ничего не найдено");
        }

        return kinSeasonsModel.getTotal();
    }

    private static List<KinResultModel> searchByKeyword(String keyword, int page) {
        ResponseEntity<KinSearchModel> response = restTemplate.exchange(
                URL + URL_SEARCH + keyword + "&page=" + page,
                HttpMethod.GET,
                request,
                KinSearchModel.class,
                1
        );
        KinSearchModel kinSearchModel = response.getBody();
        if (kinSearchModel == null) {
            throw new IllegalStateException("Ничего не найдено");
        }

        return new ArrayList<>(Arrays.asList(kinSearchModel.getKinResultModels()));
    }
}
