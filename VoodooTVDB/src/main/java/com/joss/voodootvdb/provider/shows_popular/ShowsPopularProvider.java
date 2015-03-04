package com.joss.voodootvdb.provider.shows_popular;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.model.ShowsPopularModel;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 7:05 PM
 */
public class ShowsPopularProvider {

    public static List<ShowsPopularModel> get(List<Show> shows) {
        List<ShowsPopularModel> items = new ArrayList<>();
        for(Show s : shows){
            items.add(new ShowsPopularModel(s));
        }
        return items;
    }

    public static List<Show> get(ShowsPopularCursor cursor) {
        List<Show> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                items.add(GGson.fromJson(cursor.getJson(), Show.class));
                cursor.moveToNext();
            }
        }
        return items;
    }
}
