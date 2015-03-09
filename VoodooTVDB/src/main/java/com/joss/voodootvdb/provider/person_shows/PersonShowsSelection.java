package com.joss.voodootvdb.provider.person_shows;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code person_shows} table.
 */
public class PersonShowsSelection extends AbstractSelection<PersonShowsSelection> {
    @Override
    public Uri uri() {
        return PersonShowsColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code PersonShowsCursor} object, which is positioned before the first entry, or null.
     */
    public PersonShowsCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new PersonShowsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public PersonShowsCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public PersonShowsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public PersonShowsSelection id(long... value) {
        addEquals(PersonShowsColumns._ID, toObjectArray(value));
        return this;
    }

    public PersonShowsSelection traktId(Integer... value) {
        addEquals(PersonShowsColumns.TRAKT_ID, value);
        return this;
    }
    
    public PersonShowsSelection traktIdNot(Integer... value) {
        addNotEquals(PersonShowsColumns.TRAKT_ID, value);
        return this;
    }

    public PersonShowsSelection traktIdGt(int value) {
        addGreaterThan(PersonShowsColumns.TRAKT_ID, value);
        return this;
    }

    public PersonShowsSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(PersonShowsColumns.TRAKT_ID, value);
        return this;
    }

    public PersonShowsSelection traktIdLt(int value) {
        addLessThan(PersonShowsColumns.TRAKT_ID, value);
        return this;
    }

    public PersonShowsSelection traktIdLtEq(int value) {
        addLessThanOrEquals(PersonShowsColumns.TRAKT_ID, value);
        return this;
    }

    public PersonShowsSelection json(String... value) {
        addEquals(PersonShowsColumns.JSON, value);
        return this;
    }
    
    public PersonShowsSelection jsonNot(String... value) {
        addNotEquals(PersonShowsColumns.JSON, value);
        return this;
    }

}
