package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.provider.episodes.EpisodesCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code episodes}.
 */
public class EpisodesModel{
    public Integer showTraktId;
    public Integer episodeTraktId;
    public Integer season;
    public Integer number;
    public String firstAired;
    public String updatedAt;
    public String json;

    public EpisodesModel(){}

    public EpisodesModel(EpisodesCursor cursor){
        this.showTraktId = cursor.getShowTraktId();
        this.episodeTraktId = cursor.getEpisodeTraktId();
        this.season = cursor.getSeason();
        this.number = cursor.getNumber();
        this.firstAired = cursor.getFirstAired();
        this.updatedAt = cursor.getUpdatedAt();
        this.json = cursor.getJson();
    }

    public EpisodesModel(int showTraktId, Episode e) {
        this.showTraktId = showTraktId;
        this.episodeTraktId = e.getIds().getTrakt();
        this.season = e.getSeason();
        this.number = e.getNumber();
        this.firstAired = e.getFirst_aired();
        this.updatedAt = e.getUpdated_at();
        this.json = GGson.toJson(e);
    }
}