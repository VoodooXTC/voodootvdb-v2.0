package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.shows.ShowsCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code shows}.
 */
public class ShowsModel{
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

    public ShowsModel(){}

    public ShowsModel(ShowsCursor cursor){
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

    public ShowsModel(Show show) {
        this.title = show.getTitle();
        this.traktId = show.getIds().getTrakt();
        this.imdbId = show.getIds().getImdb();
        this.firstAired = show.getFirstAired();
        this.country = show.getCountry();
        this.status = show.getStatus();
        this.rating = show.getRating();
        this.updatedAt = show.getUpdatedAt();
        this.language = show.getLanguage();
        this.json = GGson.toJson(show);
    }
}