package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedCursor;
import com.google.gson.annotations.SerializedName;

/**
 * Model object for the {@code shows_related}.
 */
public class ShowsRelatedModel{
    public Integer showTraktId;
    public Integer relatedTraktId;

    public ShowsRelatedModel(){}

    public ShowsRelatedModel(ShowsRelatedCursor cursor){
        this.showTraktId = cursor.getShowTraktId();
        this.relatedTraktId = cursor.getRelatedTraktId();
    }

    public ShowsRelatedModel(int showTraktId, Show show) {
        this.showTraktId = showTraktId;
        this.relatedTraktId = show.getIds().getTrakt();
    }
}