package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.provider.seasons.SeasonsCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code seasons}.
 */
public class SeasonsModel{
    public Integer showTraktId;
    public Integer seasonTraktId;
    public Integer number;
    public String json;

    public SeasonsModel(){}

    public SeasonsModel(SeasonsCursor cursor){
        this.showTraktId = cursor.getShowTraktId();
        this.seasonTraktId = cursor.getSeasonTraktId();
        this.number = cursor.getNumber();
        this.json = cursor.getJson();
    }

    public SeasonsModel(int showTraktId, Season s) {
        this.showTraktId = showTraktId;
        this.seasonTraktId = s.getIds().getTrakt();
        this.number = s.getNumber();
        this.json = GGson.toJson(s);
    }
}