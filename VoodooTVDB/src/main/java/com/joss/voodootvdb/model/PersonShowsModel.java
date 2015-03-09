package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.provider.person_shows.PersonShowsCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code person_shows}.
 */
public class PersonShowsModel{
    public Integer traktId;
    public String json;

    public PersonShowsModel(){}

    public PersonShowsModel(PersonShowsCursor cursor){
        this.traktId = cursor.getTraktId();
        this.json = cursor.getJson();
    }

    public PersonShowsModel(int traktId, People people) {
        this.traktId = traktId;
        this.json = GGson.toJson(people);
    }
}