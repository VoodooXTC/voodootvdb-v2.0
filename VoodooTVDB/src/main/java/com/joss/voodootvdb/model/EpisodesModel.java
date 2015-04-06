package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.provider.episodes.EpisodesCursor;
import com.joss.voodootvdb.utils.DateFormatter;
import com.joss.voodootvdb.utils.GGson;

import org.joda.time.DateTime;

/**
 * Model object for the {@code episodes}.
 */
public class EpisodesModel{
    public Integer showTraktId;
    public Integer episodeTraktId;
    public Integer season;
    public Integer number;
    public Long firstAired;
    public Long updatedAt;
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
        this.firstAired = DateFormatter.toMillis(e.getFirstAired(), 0);
        this.updatedAt = DateFormatter.toMillis(e.getUpdatedAt(), 0);
        this.json = GGson.toJson(e);
    }
}