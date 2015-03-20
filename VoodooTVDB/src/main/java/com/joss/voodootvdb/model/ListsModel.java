package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.provider.lists.ListsCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

import org.joda.time.DateTime;

/**
 * Model object for the {@code lists}.
 */
public class ListsModel{
    public Integer traktId;
    public String name;
    public String slug;
    public Long updatedAt;
    public String json;

    public ListsModel(){}

    public ListsModel(ListsCursor cursor){
        this.traktId = cursor.getTraktId();
        this.name = cursor.getName();
        this.slug = cursor.getSlug();
        this.updatedAt = cursor.getUpdatedAt();
        this.json = cursor.getJson();
    }

    public ListsModel(CustomList l) {
        this.traktId = l.getIds().getTrakt();
        this.name = l.getName();
        this.slug = l.getIds().getSlug();
        this.updatedAt = new DateTime(l.getUpdatedAt()).getMillis();
        this.json = GGson.toJson(l);
    }
}