package com.joss.voodootvdb.provider.shows_related;

import android.content.Context;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.HomeItem;
import com.joss.voodootvdb.model.ShowsRelatedModel;
import com.joss.voodootvdb.provider.shows.ShowsProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/7/2015
 * Time: 11:46 PM
 */
public class ShowsRelatedProvider {

    public static List<ShowsRelatedModel> get(int showTraktId, List<Show> shows) {
        List<ShowsRelatedModel> items = new ArrayList<>();
        for(Show show : shows){
            items.add(new ShowsRelatedModel(showTraktId, show));
        }
        return items;
    }

    public static List<Show> get(Context context, ShowsRelatedCursor cursor) {
        List<Show> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Show show = ShowsProvider.get(context, cursor.getRelatedTraktId());
                if(show != null)
                    items.add(show);
                cursor.moveToNext();
            }
        }
        return items;
    }

    public static List<HomeItem> getHomeItems(Context context, ShowsRelatedCursor cursor) {
        List<HomeItem> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Show show = ShowsProvider.get(context, cursor.getRelatedTraktId());
                if(show != null)
                    items.add(show);
                cursor.moveToNext();
            }
        }
        return items;
    }
}
