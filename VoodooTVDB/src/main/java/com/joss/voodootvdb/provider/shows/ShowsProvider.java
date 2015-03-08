package com.joss.voodootvdb.provider.shows;

import android.content.Context;
import android.database.Cursor;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.model.ShowsModel;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/7/2015
 * Time: 4:03 PM
 */
public class ShowsProvider {

    public static Show get(ShowsCursor cursor) {
        if(cursor.moveToFirst()){
            return GGson.fromJson(cursor.getJson(), Show.class);
        }
        return null;
    }

    public static List<ShowsModel> get(List<Show> shows) {
        List<ShowsModel> items = new ArrayList<>();
        for(Show s : shows){
            items.add(new ShowsModel(s));
        }
        return items;
    }

    public static Show get(Context context, Integer traktId) {
        Show show = null;

        ShowsSelection where = new ShowsSelection();
        where.traktId(traktId);

        Cursor cursor = context.getContentResolver().query(ShowsColumns.CONTENT_URI, ShowsColumns.FULL_PROJECTION, where.sel(), where.args(), null);
        ShowsCursor showsCursor = new ShowsCursor(cursor);

        if(showsCursor.moveToFirst()){
            show = GGson.fromJson(showsCursor.getJson(), Show.class);
        }

        cursor.close();
        showsCursor.close();

        return show;
    }
}
