package com.joss.voodootvdb.provider.shows_popular;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.ShowsPopularModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code shows_popular} table.
 */
public class ShowsPopularContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ShowsPopularColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ShowsPopularSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ShowsPopularContentValues putShowTraktId(Integer value) {
        mContentValues.put(ShowsPopularColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsPopularContentValues putShowTraktIdNull() {
        mContentValues.putNull(ShowsPopularColumns.SHOW_TRAKT_ID);
        return this;
    }


    public static ContentValues[] getContentValues(List<ShowsPopularModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(ShowsPopularModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(ShowsPopularModel item){
        ShowsPopularContentValues values = new ShowsPopularContentValues();
        values.putShowTraktId(item.showTraktId);
        return values.values();
    }
}
