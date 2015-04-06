package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.shows.ShowsCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.DateFormatter;
import com.joss.voodootvdb.utils.GGson;

import org.joda.time.DateTime;

/**
 * Model object for the {@code shows}.
 */
public class ShowsModel{
    public String title;
    public Integer traktId;
    public String imdbId;
    public Long firstAired;
    public String country;
    public String status;
    public Double rating;
    public Long updatedAt;
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
        this.firstAired = DateFormatter.toMillis(show.getFirstAired(), 0);
        this.country = show.getCountry();
        this.status = show.getStatus();
        this.rating = show.getRating();
        this.updatedAt = DateFormatter.toMillis(show.getUpdatedAt(), 0);
        this.language = show.getLanguage();
        this.json = GGson.toJson(show);
    }
}