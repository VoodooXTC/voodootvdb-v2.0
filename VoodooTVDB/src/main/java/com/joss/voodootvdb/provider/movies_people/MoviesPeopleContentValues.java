package com.joss.voodootvdb.provider.movies_people;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.MoviesPeopleModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movies_people} table.
 */
public class MoviesPeopleContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MoviesPeopleColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, MoviesPeopleSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MoviesPeopleContentValues putTraktId(Integer value) {
        mContentValues.put(MoviesPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesPeopleContentValues putTraktIdNull() {
        mContentValues.putNull(MoviesPeopleColumns.TRAKT_ID);
        return this;
    }


    public MoviesPeopleContentValues putJson(String value) {
        mContentValues.put(MoviesPeopleColumns.JSON, value);
        return this;
    }

    public MoviesPeopleContentValues putJsonNull() {
        mContentValues.putNull(MoviesPeopleColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(MoviesPeopleModel... items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(MoviesPeopleModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(MoviesPeopleModel item){
        MoviesPeopleContentValues values = new MoviesPeopleContentValues();
        values.putTraktId(item.traktId);
        values.putJson(item.json);
        return values.values();
    }
}
