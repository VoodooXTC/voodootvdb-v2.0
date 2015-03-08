package com.joss.voodootvdb.api;

import android.content.ContentValues;
import android.content.Context;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.model.ShowsModel;
import com.joss.voodootvdb.provider.shows.ShowsColumns;
import com.joss.voodootvdb.provider.shows.ShowsContentValues;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularContentValues;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularProvider;

import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 6:04 PM
 */
public class Db {

    public static void updatePopularShows(Context context, List<Show> shows){
        context.getContentResolver().delete(ShowsPopularColumns.CONTENT_URI, null, null);
        ContentValues[] showsPopularCV = ShowsPopularContentValues.getContentValues(ShowsPopularProvider.get(shows));
        context.getContentResolver().bulkInsert(ShowsPopularColumns.CONTENT_URI, showsPopularCV);
    }

    public static void updateShow(Context context, Show show) {
        ContentValues showCV = ShowsContentValues.getSingleContentValue(new ShowsModel(show));
        context.getContentResolver().insert(ShowsColumns.CONTENT_URI, showCV);
    }
}
