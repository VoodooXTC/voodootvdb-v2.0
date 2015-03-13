package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Progress.Episode;
import com.joss.voodootvdb.provider.episodes_watched.EpisodesWatchedCursor;
import com.google.gson.annotations.SerializedName;

/**
 * Model object for the {@code episodes_watched}.
 */
public class EpisodesWatchedModel{
    public Integer showTraktId;
    public Integer season;
    public Integer number;
    public Boolean completed;

    public EpisodesWatchedModel(){}

    public EpisodesWatchedModel(EpisodesWatchedCursor cursor){
        this.showTraktId = cursor.getShowTraktId();
        this.season = cursor.getSeason();
        this.number = cursor.getNumber();
        this.completed = cursor.getCompleted();
    }

    public EpisodesWatchedModel(int showTraktId, Integer seasonNumber, Episode e) {
        this.showTraktId = showTraktId;
        this.season = seasonNumber;
        this.number = e.getNumber();
        this.completed = e.getCompleted();
    }
}