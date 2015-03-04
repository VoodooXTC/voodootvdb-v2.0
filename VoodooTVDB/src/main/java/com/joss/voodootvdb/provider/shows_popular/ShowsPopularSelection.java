package com.joss.voodootvdb.provider.shows_popular;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code shows_popular} table.
 */
public class ShowsPopularSelection extends AbstractSelection<ShowsPopularSelection> {
    @Override
    public Uri uri() {
        return ShowsPopularColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ShowsPopularCursor} object, which is positioned before the first entry, or null.
     */
    public ShowsPopularCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ShowsPopularCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ShowsPopularCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ShowsPopularCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public ShowsPopularSelection id(long... value) {
        addEquals(ShowsPopularColumns._ID, toObjectArray(value));
        return this;
    }

    public ShowsPopularSelection title(String... value) {
        addEquals(ShowsPopularColumns.TITLE, value);
        return this;
    }
    
    public ShowsPopularSelection titleNot(String... value) {
        addNotEquals(ShowsPopularColumns.TITLE, value);
        return this;
    }


    public ShowsPopularSelection traktId(Integer... value) {
        addEquals(ShowsPopularColumns.TRAKT_ID, value);
        return this;
    }
    
    public ShowsPopularSelection traktIdNot(Integer... value) {
        addNotEquals(ShowsPopularColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection traktIdGt(int value) {
        addGreaterThan(ShowsPopularColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(ShowsPopularColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection traktIdLt(int value) {
        addLessThan(ShowsPopularColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection traktIdLtEq(int value) {
        addLessThanOrEquals(ShowsPopularColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection imdbId(String... value) {
        addEquals(ShowsPopularColumns.IMDB_ID, value);
        return this;
    }
    
    public ShowsPopularSelection imdbIdNot(String... value) {
        addNotEquals(ShowsPopularColumns.IMDB_ID, value);
        return this;
    }


    public ShowsPopularSelection firstAired(String... value) {
        addEquals(ShowsPopularColumns.FIRST_AIRED, value);
        return this;
    }
    
    public ShowsPopularSelection firstAiredNot(String... value) {
        addNotEquals(ShowsPopularColumns.FIRST_AIRED, value);
        return this;
    }


    public ShowsPopularSelection country(String... value) {
        addEquals(ShowsPopularColumns.COUNTRY, value);
        return this;
    }
    
    public ShowsPopularSelection countryNot(String... value) {
        addNotEquals(ShowsPopularColumns.COUNTRY, value);
        return this;
    }


    public ShowsPopularSelection status(String... value) {
        addEquals(ShowsPopularColumns.STATUS, value);
        return this;
    }
    
    public ShowsPopularSelection statusNot(String... value) {
        addNotEquals(ShowsPopularColumns.STATUS, value);
        return this;
    }


    public ShowsPopularSelection rating(Double... value) {
        addEquals(ShowsPopularColumns.RATING, value);
        return this;
    }
    
    public ShowsPopularSelection ratingNot(Double... value) {
        addNotEquals(ShowsPopularColumns.RATING, value);
        return this;
    }

    public ShowsPopularSelection ratingGt(double value) {
        addGreaterThan(ShowsPopularColumns.RATING, value);
        return this;
    }

    public ShowsPopularSelection ratingGtEq(double value) {
        addGreaterThanOrEquals(ShowsPopularColumns.RATING, value);
        return this;
    }

    public ShowsPopularSelection ratingLt(double value) {
        addLessThan(ShowsPopularColumns.RATING, value);
        return this;
    }

    public ShowsPopularSelection ratingLtEq(double value) {
        addLessThanOrEquals(ShowsPopularColumns.RATING, value);
        return this;
    }

    public ShowsPopularSelection updatedAt(String... value) {
        addEquals(ShowsPopularColumns.UPDATED_AT, value);
        return this;
    }
    
    public ShowsPopularSelection updatedAtNot(String... value) {
        addNotEquals(ShowsPopularColumns.UPDATED_AT, value);
        return this;
    }


    public ShowsPopularSelection language(String... value) {
        addEquals(ShowsPopularColumns.LANGUAGE, value);
        return this;
    }
    
    public ShowsPopularSelection languageNot(String... value) {
        addNotEquals(ShowsPopularColumns.LANGUAGE, value);
        return this;
    }


    public ShowsPopularSelection json(String... value) {
        addEquals(ShowsPopularColumns.JSON, value);
        return this;
    }
    
    public ShowsPopularSelection jsonNot(String... value) {
        addNotEquals(ShowsPopularColumns.JSON, value);
        return this;
    }

}
