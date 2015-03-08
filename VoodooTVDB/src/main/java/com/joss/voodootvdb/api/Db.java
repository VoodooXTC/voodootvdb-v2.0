package com.joss.voodootvdb.api;

import android.content.ContentValues;
import android.content.Context;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.model.ShowsModel;
import com.joss.voodootvdb.provider.shows.ShowsColumns;
import com.joss.voodootvdb.provider.shows.ShowsContentValues;
import com.joss.voodootvdb.provider.shows.ShowsProvider;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularContentValues;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularProvider;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularSelection;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedColumns;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedContentValues;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedProvider;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedSelection;

import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 6:04 PM
 */
public class Db {

    public static void updateShow(Context context, Show show) {
        ContentValues showCV = ShowsContentValues.getSingleContentValue(new ShowsModel(show));
        context.getContentResolver().insert(ShowsColumns.CONTENT_URI, showCV);
    }

    public static void updatePopularShows(Context context, List<Show> shows){
        // Update Show Content
        ContentValues[] showsCV = ShowsContentValues.getContentValues(ShowsProvider.get(shows));
        context.getContentResolver().bulkInsert(ShowsColumns.CONTENT_URI, showsCV);

        // Update Popular Shows
        context.getContentResolver().delete(ShowsPopularColumns.CONTENT_URI, null, null);
        ContentValues[] showsPopularCV = ShowsPopularContentValues.getContentValues(ShowsPopularProvider.get(shows));
        context.getContentResolver().bulkInsert(ShowsPopularColumns.CONTENT_URI, showsPopularCV);
    }

    public static void updateShowsRelated(Context context, int traktId, List<Show> shows) {
        // Update Show Content
        ContentValues[] showsCV = ShowsContentValues.getContentValues(ShowsProvider.get(shows));
        context.getContentResolver().bulkInsert(ShowsColumns.CONTENT_URI, showsCV);

        // Update Related Shows
        ShowsRelatedSelection where = new ShowsRelatedSelection();
        where.showTraktId(traktId);
        context.getContentResolver().delete(ShowsRelatedColumns.CONTENT_URI, where.sel(), where.args());
        ContentValues[] showsRelatedCV = ShowsRelatedContentValues.getContentValues(ShowsRelatedProvider.get(traktId, shows));
        context.getContentResolver().bulkInsert(ShowsRelatedColumns.CONTENT_URI, showsRelatedCV);
    }
}
