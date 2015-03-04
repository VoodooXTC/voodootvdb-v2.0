package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code shows_popular}.
 */
public class ShowsPopularModel{
    public String title;
    public Integer traktId;
    public String imdbId;
    public String firstAired;
    public String country;
    public String status;
    public Double rating;
    public String updatedAt;
    public String language;
    public String json;

    public ShowsPopularModel(){}

    public ShowsPopularModel(ShowsPopularCursor cursor){
        this.title = cursor.getTitle();
        this.traktId = cursor.getTraktId();
        this.imdbId = cursor.getImdbId();
        this.firstAired = cursor.getFirstAired();
        this.country = cursor.getCountry();
        this.status = cursor.getStatus();
        this.rating = cursor.getRating();
        this.updatedAt = cursor.getUpdatedAt();
        this.language = cursor.getLanguage();
        this.json = cursor.getJson();
    }

    public ShowsPopularModel(Show s) {
        this.title = s.getTitle();
        this.traktId = s.getIds().getTrakt();
        this.imdbId = s.getIds().getImdb();
        this.firstAired = s.getFirstAired();
        this.country = s.getCountry();
        this.status = s.getStatus();
        this.rating = s.getRating();
        this.updatedAt = s.getUpdatedAt();
        this.language = s.getLanguage();
        this.json = GGson.toJson(s);
    }
}