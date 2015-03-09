package com.joss.voodootvdb.provider.movies_people;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code movies_people} table.
 */
public class MoviesPeopleSelection extends AbstractSelection<MoviesPeopleSelection> {
    @Override
    public Uri uri() {
        return MoviesPeopleColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code MoviesPeopleCursor} object, which is positioned before the first entry, or null.
     */
    public MoviesPeopleCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new MoviesPeopleCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public MoviesPeopleCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public MoviesPeopleCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public MoviesPeopleSelection id(long... value) {
        addEquals(MoviesPeopleColumns._ID, toObjectArray(value));
        return this;
    }

    public MoviesPeopleSelection traktId(Integer... value) {
        addEquals(MoviesPeopleColumns.TRAKT_ID, value);
        return this;
    }
    
    public MoviesPeopleSelection traktIdNot(Integer... value) {
        addNotEquals(MoviesPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesPeopleSelection traktIdGt(int value) {
        addGreaterThan(MoviesPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesPeopleSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(MoviesPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesPeopleSelection traktIdLt(int value) {
        addLessThan(MoviesPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesPeopleSelection traktIdLtEq(int value) {
        addLessThanOrEquals(MoviesPeopleColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesPeopleSelection json(String... value) {
        addEquals(MoviesPeopleColumns.JSON, value);
        return this;
    }
    
    public MoviesPeopleSelection jsonNot(String... value) {
        addNotEquals(MoviesPeopleColumns.JSON, value);
        return this;
    }

}
