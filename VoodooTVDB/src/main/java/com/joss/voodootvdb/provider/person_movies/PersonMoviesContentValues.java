package com.joss.voodootvdb.provider.person_movies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.PersonMoviesModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code person_movies} table.
 */
public class PersonMoviesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PersonMoviesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, PersonMoviesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PersonMoviesContentValues putTraktId(Integer value) {
        mContentValues.put(PersonMoviesColumns.TRAKT_ID, value);
        return this;
    }

    public PersonMoviesContentValues putTraktIdNull() {
        mContentValues.putNull(PersonMoviesColumns.TRAKT_ID);
        return this;
    }


    public PersonMoviesContentValues putJson(String value) {
        mContentValues.put(PersonMoviesColumns.JSON, value);
        return this;
    }

    public PersonMoviesContentValues putJsonNull() {
        mContentValues.putNull(PersonMoviesColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(PersonMoviesModel... items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(PersonMoviesModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(PersonMoviesModel item){
        PersonMoviesContentValues values = new PersonMoviesContentValues();
        values.putTraktId(item.traktId);
        values.putJson(item.json);
        return values.values();
    }
}
