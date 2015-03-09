package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code movies_people}.
 */
public class MoviesPeopleModel{
    public Integer traktId;
    public String json;

    public MoviesPeopleModel(){}

    public MoviesPeopleModel(MoviesPeopleCursor cursor){
        this.traktId = cursor.getTraktId();
        this.json = cursor.getJson();
    }

    public MoviesPeopleModel(int traktId, People moviesPeople) {
        this.traktId = traktId;
        this.json = GGson.toJson(moviesPeople);
    }
}