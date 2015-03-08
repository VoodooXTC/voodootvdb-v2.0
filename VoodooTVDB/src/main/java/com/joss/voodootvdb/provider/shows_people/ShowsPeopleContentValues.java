package com.joss.voodootvdb.provider.shows_people;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.ShowsPeopleModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code shows_people} table.
 */
public class ShowsPeopleContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ShowsPeopleColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ShowsPeopleSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ShowsPeopleContentValues putTraktId(Integer value) {
        mContentValues.put(ShowsPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPeopleContentValues putTraktIdNull() {
        mContentValues.putNull(ShowsPeopleColumns.TRAKT_ID);
        return this;
    }


    public ShowsPeopleContentValues putJson(String value) {
        mContentValues.put(ShowsPeopleColumns.JSON, value);
        return this;
    }

    public ShowsPeopleContentValues putJsonNull() {
        mContentValues.putNull(ShowsPeopleColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(ShowsPeopleModel... items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(ShowsPeopleModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(ShowsPeopleModel item){
        ShowsPeopleContentValues values = new ShowsPeopleContentValues();
        values.putTraktId(item.traktId);
        values.putJson(item.json);
        return values.values();
    }
}
