package com.joss.voodootvdb.provider.person;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code person} table.
 */
public class PersonSelection extends AbstractSelection<PersonSelection> {
    @Override
    public Uri uri() {
        return PersonColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code PersonCursor} object, which is positioned before the first entry, or null.
     */
    public PersonCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new PersonCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public PersonCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public PersonCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public PersonSelection id(long... value) {
        addEquals(PersonColumns._ID, toObjectArray(value));
        return this;
    }

    public PersonSelection name(String... value) {
        addEquals(PersonColumns.NAME, value);
        return this;
    }
    
    public PersonSelection nameNot(String... value) {
        addNotEquals(PersonColumns.NAME, value);
        return this;
    }


    public PersonSelection traktId(Integer... value) {
        addEquals(PersonColumns.TRAKT_ID, value);
        return this;
    }
    
    public PersonSelection traktIdNot(Integer... value) {
        addNotEquals(PersonColumns.TRAKT_ID, value);
        return this;
    }

    public PersonSelection traktIdGt(int value) {
        addGreaterThan(PersonColumns.TRAKT_ID, value);
        return this;
    }

    public PersonSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(PersonColumns.TRAKT_ID, value);
        return this;
    }

    public PersonSelection traktIdLt(int value) {
        addLessThan(PersonColumns.TRAKT_ID, value);
        return this;
    }

    public PersonSelection traktIdLtEq(int value) {
        addLessThanOrEquals(PersonColumns.TRAKT_ID, value);
        return this;
    }

    public PersonSelection json(String... value) {
        addEquals(PersonColumns.JSON, value);
        return this;
    }
    
    public PersonSelection jsonNot(String... value) {
        addNotEquals(PersonColumns.JSON, value);
        return this;
    }

}
