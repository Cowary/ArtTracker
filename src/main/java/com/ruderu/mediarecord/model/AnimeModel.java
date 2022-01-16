package com.ruderu.mediarecord.model;

public class AnimeModel {

    private int id;
    private String name;
    private String russian;
    //image
    private String url;
    private String kind;
    private String score;
    private String status;
    private int episodes;
    private int episodes_aired;
    private String aired_on;
    private String released_on;

    public AnimeModel() {
    }

    public AnimeModel(int id, String name, String russian, String url, String kind, String score, String status, int episodes, int episodes_aired, String aired_on, String released_on) {
        this.id = id;
        this.name = name;
        this.russian = russian;
        this.url = url;
        this.kind = kind;
        this.score = score;
        this.status = status;
        this.episodes = episodes;
        this.episodes_aired = episodes_aired;
        this.aired_on = aired_on;
        this.released_on = released_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getEpisodes_aired() {
        return episodes_aired;
    }

    public void setEpisodes_aired(int episodes_aired) {
        this.episodes_aired = episodes_aired;
    }

    public String getAired_on() {
        return aired_on;
    }

    public void setAired_on(String aired_on) {
        this.aired_on = aired_on;
    }

    public String getReleased_on() {
        return released_on;
    }

    public void setReleased_on(String released_on) {
        this.released_on = released_on;
    }

    @Override
    public String toString() {
        return "AnimeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", russian='" + russian + '\'' +
                ", url='" + url + '\'' +
                ", kind='" + kind + '\'' +
                ", score='" + score + '\'' +
                ", status='" + status + '\'' +
                ", episodes=" + episodes +
                ", episodes_aired=" + episodes_aired +
                ", aired_on='" + aired_on + '\'' +
                ", released_on='" + released_on + '\'' +
                '}';
    }
}
