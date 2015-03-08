package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code shows_people}.
 */
public class ShowsPeopleModel{
    public Integer traktId;
    public String json;

    public ShowsPeopleModel(){}

    public ShowsPeopleModel(ShowsPeopleCursor cursor){
        this.traktId = cursor.getTraktId();
        this.json = cursor.getJson();
    }

    public ShowsPeopleModel(int traktId, People showsPeople) {
        this.traktId = traktId;
        this.json = GGson.toJson(showsPeople);
    }
}