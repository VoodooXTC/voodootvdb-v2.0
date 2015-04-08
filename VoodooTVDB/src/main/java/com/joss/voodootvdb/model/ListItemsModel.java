package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.provider.list_items.ListItemsCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.DateFormatter;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code list_items}.
 */
public class ListItemsModel{
    public Integer listTraktId;
    public Integer itemtraktid;
    public Long listedAt;
    public String type;
    public String json;

    public ListItemsModel(){}

    public ListItemsModel(ListItemsCursor cursor){
        this.listTraktId = cursor.getListTraktId();
        this.itemtraktid = cursor.getItemtraktid();
        this.listedAt = cursor.getListedAt();
        this.type = cursor.getType();
        this.json = cursor.getJson();
    }

    public ListItemsModel(int listTraktId, CustomListItem c) {
        this.listTraktId = listTraktId;
        this.itemtraktid = c.getTraktId();
        this.listedAt = DateFormatter.toMillis(c.getListedAt(), 0);
        this.type = c.getType();
        this.json = GGson.toJson(c);
    }
}