package com.joss.voodootvdb.provider.shows_people;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code shows_people} table.
 */
public class ShowsPeopleSelection extends AbstractSelection<ShowsPeopleSelection> {
    @Override
    public Uri uri() {
        return ShowsPeopleColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ShowsPeopleCursor} object, which is positioned before the first entry, or null.
     */
    public ShowsPeopleCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ShowsPeopleCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ShowsPeopleCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ShowsPeopleCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public ShowsPeopleSelection id(long... value) {
        addEquals(ShowsPeopleColumns._ID, toObjectArray(value));
        return this;
    }

    public ShowsPeopleSelection traktId(Integer... value) {
        addEquals(ShowsPeopleColumns.TRAKT_ID, value);
        return this;
    }
    
    public ShowsPeopleSelection traktIdNot(Integer... value) {
        addNotEquals(ShowsPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPeopleSelection traktIdGt(int value) {
        addGreaterThan(ShowsPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPeopleSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(ShowsPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPeopleSelection traktIdLt(int value) {
        addLessThan(ShowsPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPeopleSelection traktIdLtEq(int value) {
        addLessThanOrEquals(ShowsPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPeopleSelection json(String... value) {
        addEquals(ShowsPeopleColumns.JSON, value);
        return this;
    }
    
    public ShowsPeopleSelection jsonNot(String... value) {
        addNotEquals(ShowsPeopleColumns.JSON, value);
        return this;
    }

}
