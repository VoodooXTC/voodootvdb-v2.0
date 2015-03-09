package com.joss.voodootvdb.provider.person_movies;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code person_movies} table.
 */
public class PersonMoviesSelection extends AbstractSelection<PersonMoviesSelection> {
    @Override
    public Uri uri() {
        return PersonMoviesColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code PersonMoviesCursor} object, which is positioned before the first entry, or null.
     */
    public PersonMoviesCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new PersonMoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public PersonMoviesCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public PersonMoviesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public PersonMoviesSelection id(long... value) {
        addEquals(PersonMoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public PersonMoviesSelection traktId(Integer... value) {
        addEquals(PersonMoviesColumns.TRAKT_ID, value);
        return this;
    }
    
    public PersonMoviesSelection traktIdNot(Integer... value) {
        addNotEquals(PersonMoviesColumns.TRAKT_ID, value);
        return this;
    }

    public PersonMoviesSelection traktIdGt(int value) {
        addGreaterThan(PersonMoviesColumns.TRAKT_ID, value);
        return this;
    }

    public PersonMoviesSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(PersonMoviesColumns.TRAKT_ID, value);
        return this;
    }

    public PersonMoviesSelection traktIdLt(int value) {
        addLessThan(PersonMoviesColumns.TRAKT_ID, value);
        return this;
    }

    public PersonMoviesSelection traktIdLtEq(int value) {
        addLessThanOrEquals(PersonMoviesColumns.TRAKT_ID, value);
        return this;
    }

    public PersonMoviesSelection json(String... value) {
        addEquals(PersonMoviesColumns.JSON, value);
        return this;
    }
    
    public PersonMoviesSelection jsonNot(String... value) {
        addNotEquals(PersonMoviesColumns.JSON, value);
        return this;
    }

}
