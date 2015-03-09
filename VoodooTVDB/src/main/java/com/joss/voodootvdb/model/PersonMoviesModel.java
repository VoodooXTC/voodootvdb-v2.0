package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code person_movies}.
 */
public class PersonMoviesModel{
    public Integer traktId;
    public String json;

    public PersonMoviesModel(){}

    public PersonMoviesModel(PersonMoviesCursor cursor){
        this.traktId = cursor.getTraktId();
        this.json = cursor.getJson();
    }

    public PersonMoviesModel(int traktId, People people) {
        this.traktId = traktId;
        this.json = GGson.toJson(people);
    }
}