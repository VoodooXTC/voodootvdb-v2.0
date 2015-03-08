package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularCursor;
import com.google.gson.annotations.SerializedName;

/**
 * Model object for the {@code shows_popular}.
 */
public class ShowsPopularModel{
    public Integer showTraktId;

    public ShowsPopularModel(){}

    public ShowsPopularModel(ShowsPopularCursor cursor){
        this.showTraktId = cursor.getShowTraktId();
    }

    public ShowsPopularModel(Show s) {
        this.showTraktId = s.getIds().getTrakt();
    }
}