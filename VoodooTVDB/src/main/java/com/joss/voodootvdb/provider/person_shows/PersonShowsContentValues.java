package com.joss.voodootvdb.provider.person_shows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.PersonShowsModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code person_shows} table.
 */
public class PersonShowsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PersonShowsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, PersonShowsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PersonShowsContentValues putTraktId(Integer value) {
        mContentValues.put(PersonShowsColumns.TRAKT_ID, value);
        return this;
    }

    public PersonShowsContentValues putTraktIdNull() {
        mContentValues.putNull(PersonShowsColumns.TRAKT_ID);
        return this;
    }


    public PersonShowsContentValues putJson(String value) {
        mContentValues.put(PersonShowsColumns.JSON, value);
        return this;
    }

    public PersonShowsContentValues putJsonNull() {
        mContentValues.putNull(PersonShowsColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(PersonShowsModel... items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(PersonShowsModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(PersonShowsModel item){
        PersonShowsContentValues values = new PersonShowsContentValues();
        values.putTraktId(item.traktId);
        values.putJson(item.json);
        return values.values();
    }
}
