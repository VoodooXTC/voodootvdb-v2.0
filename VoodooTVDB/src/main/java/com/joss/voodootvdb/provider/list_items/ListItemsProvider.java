package com.joss.voodootvdb.provider.list_items;

import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.model.ListItemsModel;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 1:59 PM
 */
public class ListItemsProvider {
    public static List<ListItemsModel> get(int listTraktId, List<CustomListItem> listItems) {
        List<ListItemsModel> items = new ArrayList<>();
        for(CustomListItem c : listItems){
            items.add(new ListItemsModel(listTraktId, c));
        }
        return items;
    }

    public static List<CustomListItem> get(ListItemsCursor cursor) {
        List<CustomListItem> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                items.add(GGson.fromJson(cursor.getJson(), CustomListItem.class));
                cursor.moveToNext();
            }
        }
        return items;
    }
}
