package com.joss.voodootvdb.provider.lists;

import android.content.ContentValues;
import android.content.Context;

import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.model.ListsModel;
import com.joss.voodootvdb.utils.GGson;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 1:51 PM
 */
public class ListsProvider {

    public static ListsModel getListModel(Context context, int listTraktId){
        ListsSelection where = new ListsSelection();
        where.traktId(listTraktId);
        ListsCursor cursor = where.query(context.getContentResolver());

        ListsModel l = new ListsModel();
        if(cursor.moveToFirst()){
            l = new ListsModel(cursor);
        }

        cursor.close();
        return l;
    }

    public static List<ListsModel> get(Context context, List<CustomList> lists, List<Integer> currentLists, Long millis) {
        List<ListsModel> items = new ArrayList<>();
        for(CustomList l : lists){
            ListsModel list = new ListsModel(l);

            if(!currentLists.contains(list.traktId)) {
                list.updatedAt = millis == null ? 0 : millis;
            }else{
                list.updatedAt = getListModel(context, list.traktId).updatedAt;
            }

            items.add(list);
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

    public static boolean isLastUpdatedMoreThanOneWeekAgo(Context context, int traktId){
        ListsSelection where = new ListsSelection();
        where.traktId(traktId);
        ListsCursor cursor = where.query(context.getContentResolver());
        if(cursor.moveToFirst()){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 7);

            boolean result = cursor.getUpdatedAt() < c.getTimeInMillis();
            cursor.close();

            return result;
        }

        cursor.close();
        return true;
    }

    public static CustomList get(Context context, int listTraktId) {
        ListsSelection where = new ListsSelection();
        where.traktId(listTraktId);
        ListsCursor cursor = where.query(context.getContentResolver());
        if(cursor.moveToFirst()){
            CustomList model = GGson.fromJson(cursor.getJson(), CustomList.class);

            cursor.close();
            return model;
        }

        cursor.close();
        return null;
    }

    public static void update(Context context, ListsModel model){
        ListsSelection where = new ListsSelection();
        where.traktId(model.traktId);

        ContentValues listCV = ListsContentValues.getSingleContentValue(model);
        context.getContentResolver().update(ListsColumns.CONTENT_URI, listCV, where.sel(), where.args());
    }

    public static void update(Context context, CustomList customList) {
        ListsModel listsModel = new ListsModel(customList);
        listsModel.updatedAt = DateTime.now().getMillis();

        update(context, listsModel);
    }

    public static void delete(Context context, int listTraktId) {
        ListsSelection where = new ListsSelection();
        where.traktId(listTraktId);
        context.getContentResolver().delete(ListsColumns.CONTENT_URI, where.sel(), where.args());
    }

    public static void markListStale(Context context, int listTraktId) {
        ListsModel listsModel = getListModel(context, listTraktId);
        listsModel.updatedAt = (long) 0;

        update(context, listsModel);
    }
}
