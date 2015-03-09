package com.joss.voodootvdb.provider.person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.PersonModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code person} table.
 */
public class PersonContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PersonColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, PersonSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PersonContentValues putName(String value) {
        mContentValues.put(PersonColumns.NAME, value);
        return this;
    }

    public PersonContentValues putNameNull() {
        mContentValues.putNull(PersonColumns.NAME);
        return this;
    }


    public PersonContentValues putTraktId(Integer value) {
        mContentValues.put(PersonColumns.TRAKT_ID, value);
        return this;
    }

    public PersonContentValues putTraktIdNull() {
        mContentValues.putNull(PersonColumns.TRAKT_ID);
        return this;
    }


    public PersonContentValues putJson(String value) {
        mContentValues.put(PersonColumns.JSON, value);
        return this;
    }

    public PersonContentValues putJsonNull() {
        mContentValues.putNull(PersonColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(PersonModel... items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(PersonModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(PersonModel item){
        PersonContentValues values = new PersonContentValues();
        values.putName(item.name);
        values.putTraktId(item.traktId);
        values.putJson(item.json);
        return values.values();
    }
}
