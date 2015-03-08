package com.joss.voodootvdb.provider.shows_related;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.ShowsRelatedModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code shows_related} table.
 */
public class ShowsRelatedContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ShowsRelatedColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ShowsRelatedSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ShowsRelatedContentValues putShowTraktId(Integer value) {
        mContentValues.put(ShowsRelatedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedContentValues putShowTraktIdNull() {
        mContentValues.putNull(ShowsRelatedColumns.SHOW_TRAKT_ID);
        return this;
    }


    public ShowsRelatedContentValues putRelatedTraktId(Integer value) {
        mContentValues.put(ShowsRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedContentValues putRelatedTraktIdNull() {
        mContentValues.putNull(ShowsRelatedColumns.RELATED_TRAKT_ID);
        return this;
    }


    public static ContentValues[] getContentValues(List<ShowsRelatedModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(ShowsRelatedModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(ShowsRelatedModel item){
        ShowsRelatedContentValues values = new ShowsRelatedContentValues();
        values.putShowTraktId(item.showTraktId);
        values.putRelatedTraktId(item.relatedTraktId);
        return values.values();
    }
}
