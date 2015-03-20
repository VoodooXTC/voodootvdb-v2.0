package com.joss.voodootvdb.provider.lists;

import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.model.ListsModel;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 1:51 PM
 */
public class ListsProvider {
    public static List<ListsModel> get(List<CustomList> lists) {
        List<ListsModel> items = new ArrayList<>();
        for(CustomList l : lists){
            items.add(new ListsModel(l));
        }
        return items;
    }

    public static List<CustomList> get(ListsCursor cursor) {
        List<CustomList> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                items.add(GGson.fromJson(cursor.getJson(), CustomList.class));
                cursor.moveToNext();
            }
        }
        return items;
    }
}
