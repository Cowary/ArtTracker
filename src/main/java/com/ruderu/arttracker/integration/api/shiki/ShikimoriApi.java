package com.ruderu.arttracker.integration.api.shiki;

public class ShikimoriApi {

    public static AnimeApi animeApi() {
        return new AnimeApi();
    }

    public static MangaApi mangaApi() {
        return new MangaApi();
    }

    public static RanobeApi ranobeApi() {
        return new RanobeApi();
    }


}
