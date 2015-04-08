package com.joss.voodootvdb.provider.list_items;

import android.content.ContentValues;
import android.content.Context;
import android.util.SparseArray;

import com.joss.voodootvdb.api.models.CustomLists.CustomList;
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

    public static void update(Context context, int listTraktId, List<CustomListItem> listItems, boolean deletePreviousItems) {
        if(deletePreviousItems){
            // Remove all items in the list
            ListItemsSelection where = new ListItemsSelection();
            where.listTraktId(listTraktId);
            context.getContentResolver().delete(ListItemsColumns.CONTENT_URI, where.sel(), where.args());
        }

        // Insert New Items
        ContentValues[] listItemsCV = ListItemsContentValues.getContentValues(ListItemsProvider.get(listTraktId, listItems));
        context.getContentResolver().bulkInsert(ListItemsColumns.CONTENT_URI, listItemsCV);
    }

    public static SparseArray<Boolean> getSelection(Context context, int itemTraktId, List<CustomList> lists) {
        SparseArray<Boolean> selection = new SparseArray<>();
        for(int i = 0; i < lists.size(); i++){
            int listTraktId = lists.get(i).getIds().getTrakt();
            selection.put(listTraktId, contains(context, listTraktId, itemTraktId));
        }
        return selection;
    }

    public static Boolean contains(Context context, Integer listTraktId, int itemTraktId) {
        ListItemsSelection where = new ListItemsSelection();
        where.listTraktId(listTraktId).and().itemtraktid(itemTraktId);
        ListItemsCursor cursor = where.query(context.getContentResolver());

        boolean result = cursor.moveToFirst();
        cursor.close();

        return result;
    }
}
