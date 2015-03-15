package com.joss.voodootvdb.provider.movies;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code movies} table.
 */
public class MoviesSelection extends AbstractSelection<MoviesSelection> {
    @Override
    public Uri uri() {
        return MoviesColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code MoviesCursor} object, which is positioned before the first entry, or null.
     */
    public MoviesCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new MoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public MoviesCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public MoviesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public MoviesSelection id(long... value) {
        addEquals(MoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public MoviesSelection title(String... value) {
        addEquals(MoviesColumns.TITLE, value);
        return this;
    }
    
    public MoviesSelection titleNot(String... value) {
        addNotEquals(MoviesColumns.TITLE, value);
        return this;
    }


    public MoviesSelection year(Integer... value) {
        addEquals(MoviesColumns.YEAR, value);
        return this;
    }
    
    public MoviesSelection yearNot(Integer... value) {
        addNotEquals(MoviesColumns.YEAR, value);
        return this;
    }

    public MoviesSelection yearGt(int value) {
        addGreaterThan(MoviesColumns.YEAR, value);
        return this;
    }

    public MoviesSelection yearGtEq(int value) {
        addGreaterThanOrEquals(MoviesColumns.YEAR, value);
        return this;
    }

    public MoviesSelection yearLt(int value) {
        addLessThan(MoviesColumns.YEAR, value);
        return this;
    }

    public MoviesSelection yearLtEq(int value) {
        addLessThanOrEquals(MoviesColumns.YEAR, value);
        return this;
    }

    public MoviesSelection traktId(Integer... value) {
        addEquals(MoviesColumns.TRAKT_ID, value);
        return this;
    }
    
    public MoviesSelection traktIdNot(Integer... value) {
        addNotEquals(MoviesColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesSelection traktIdGt(int value) {
        addGreaterThan(MoviesColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(MoviesColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesSelection traktIdLt(int value) {
        addLessThan(MoviesColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesSelection traktIdLtEq(int value) {
        addLessThanOrEquals(MoviesColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesSelection imdbId(String... value) {
        addEquals(MoviesColumns.IMDB_ID, value);
        return this;
    }
    
    public MoviesSelection imdbIdNot(String... value) {
        addNotEquals(MoviesColumns.IMDB_ID, value);
        return this;
    }


    public MoviesSelection released(Long... value) {
        addEquals(MoviesColumns.RELEASED, value);
        return this;
    }
    
    public MoviesSelection releasedNot(Long... value) {
        addNotEquals(MoviesColumns.RELEASED, value);
        return this;
    }

    public MoviesSelection releasedGt(long value) {
        addGreaterThan(MoviesColumns.RELEASED, value);
        return this;
    }

    public MoviesSelection releasedGtEq(long value) {
        addGreaterThanOrEquals(MoviesColumns.RELEASED, value);
        return this;
    }

    public MoviesSelection releasedLt(long value) {
        addLessThan(MoviesColumns.RELEASED, value);
        return this;
    }

    public MoviesSelection releasedLtEq(long value) {
        addLessThanOrEquals(MoviesColumns.RELEASED, value);
        return this;
    }

    public MoviesSelection rating(Double... value) {
        addEquals(MoviesColumns.RATING, value);
        return this;
    }
    
    public MoviesSelection ratingNot(Double... value) {
        addNotEquals(MoviesColumns.RATING, value);
        return this;
    }

    public MoviesSelection ratingGt(double value) {
        addGreaterThan(MoviesColumns.RATING, value);
        return this;
    }

    public MoviesSelection ratingGtEq(double value) {
        addGreaterThanOrEquals(MoviesColumns.RATING, value);
        return this;
    }

    public MoviesSelection ratingLt(double value) {
        addLessThan(MoviesColumns.RATING, value);
        return this;
    }

    public MoviesSelection ratingLtEq(double value) {
        addLessThanOrEquals(MoviesColumns.RATING, value);
        return this;
    }

    public MoviesSelection updatedAt(Long... value) {
        addEquals(MoviesColumns.UPDATED_AT, value);
        return this;
    }
    
    public MoviesSelection updatedAtNot(Long... value) {
        addNotEquals(MoviesColumns.UPDATED_AT, value);
        return this;
    }

    public MoviesSelection updatedAtGt(long value) {
        addGreaterThan(MoviesColumns.UPDATED_AT, value);
        return this;
    }

    public MoviesSelection updatedAtGtEq(long value) {
        addGreaterThanOrEquals(MoviesColumns.UPDATED_AT, value);
        return this;
    }

    public MoviesSelection updatedAtLt(long value) {
        addLessThan(MoviesColumns.UPDATED_AT, value);
        return this;
    }

    public MoviesSelection updatedAtLtEq(long value) {
        addLessThanOrEquals(MoviesColumns.UPDATED_AT, value);
        return this;
    }

    public MoviesSelection language(String... value) {
        addEquals(MoviesColumns.LANGUAGE, value);
        return this;
    }
    
    public MoviesSelection languageNot(String... value) {
        addNotEquals(MoviesColumns.LANGUAGE, value);
        return this;
    }


    public MoviesSelection json(String... value) {
        addEquals(MoviesColumns.JSON, value);
        return this;
    }
    
    public MoviesSelection jsonNot(String... value) {
        addNotEquals(MoviesColumns.JSON, value);
        return this;
    }

}
