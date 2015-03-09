package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.provider.person.PersonCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code person}.
 */
public class PersonModel{
    public String name;
    public Integer traktId;
    public String json;

    public PersonModel(){}

    public PersonModel(PersonCursor cursor){
        this.name = cursor.getName();
        this.traktId = cursor.getTraktId();
        this.json = cursor.getJson();
    }

    public PersonModel(Person person) {
        this.name = person.getName();
        this.traktId = person.getIds().getTrakt();
        this.json = GGson.toJson(person);
    }
}