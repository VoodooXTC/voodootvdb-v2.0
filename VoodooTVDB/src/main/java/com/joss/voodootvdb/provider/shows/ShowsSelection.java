package com.joss.voodootvdb.provider.shows;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code shows} table.
 */
public class ShowsSelection extends AbstractSelection<ShowsSelection> {
    @Override
    public Uri uri() {
        return ShowsColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ShowsCursor} object, which is positioned before the first entry, or null.
     */
    public ShowsCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ShowsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ShowsCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ShowsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public ShowsSelection id(long... value) {
        addEquals(ShowsColumns._ID, toObjectArray(value));
        return this;
    }

    public ShowsSelection title(String... value) {
        addEquals(ShowsColumns.TITLE, value);
        return this;
    }
    
    public ShowsSelection titleNot(String... value) {
        addNotEquals(ShowsColumns.TITLE, value);
        return this;
    }


    public ShowsSelection traktId(Integer... value) {
        addEquals(ShowsColumns.TRAKT_ID, value);
        return this;
    }
    
    public ShowsSelection traktIdNot(Integer... value) {
        addNotEquals(ShowsColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsSelection traktIdGt(int value) {
        addGreaterThan(ShowsColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(ShowsColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsSelection traktIdLt(int value) {
        addLessThan(ShowsColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsSelection traktIdLtEq(int value) {
        addLessThanOrEquals(ShowsColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsSelection imdbId(String... value) {
        addEquals(ShowsColumns.IMDB_ID, value);
        return this;
    }
    
    public ShowsSelection imdbIdNot(String... value) {
        addNotEquals(ShowsColumns.IMDB_ID, value);
        return this;
    }


    public ShowsSelection firstAired(Long... value) {
        addEquals(ShowsColumns.FIRST_AIRED, value);
        return this;
    }
    
    public ShowsSelection firstAiredNot(Long... value) {
        addNotEquals(ShowsColumns.FIRST_AIRED, value);
        return this;
    }

    public ShowsSelection firstAiredGt(long value) {
        addGreaterThan(ShowsColumns.FIRST_AIRED, value);
        return this;
    }

    public ShowsSelection firstAiredGtEq(long value) {
        addGreaterThanOrEquals(ShowsColumns.FIRST_AIRED, value);
        return this;
    }

    public ShowsSelection firstAiredLt(long value) {
        addLessThan(ShowsColumns.FIRST_AIRED, value);
        return this;
    }

    public ShowsSelection firstAiredLtEq(long value) {
        addLessThanOrEquals(ShowsColumns.FIRST_AIRED, value);
        return this;
    }

    public ShowsSelection country(String... value) {
        addEquals(ShowsColumns.COUNTRY, value);
        return this;
    }
    
    public ShowsSelection countryNot(String... value) {
        addNotEquals(ShowsColumns.COUNTRY, value);
        return this;
    }


    public ShowsSelection status(String... value) {
        addEquals(ShowsColumns.STATUS, value);
        return this;
    }
    
    public ShowsSelection statusNot(String... value) {
        addNotEquals(ShowsColumns.STATUS, value);
        return this;
    }


    public ShowsSelection rating(Double... value) {
        addEquals(ShowsColumns.RATING, value);
        return this;
    }
    
    public ShowsSelection ratingNot(Double... value) {
        addNotEquals(ShowsColumns.RATING, value);
        return this;
    }

    public ShowsSelection ratingGt(double value) {
        addGreaterThan(ShowsColumns.RATING, value);
        return this;
    }

    public ShowsSelection ratingGtEq(double value) {
        addGreaterThanOrEquals(ShowsColumns.RATING, value);
        return this;
    }

    public ShowsSelection ratingLt(double value) {
        addLessThan(ShowsColumns.RATING, value);
        return this;
    }

    public ShowsSelection ratingLtEq(double value) {
        addLessThanOrEquals(ShowsColumns.RATING, value);
        return this;
    }

    public ShowsSelection updatedAt(Long... value) {
        addEquals(ShowsColumns.UPDATED_AT, value);
        return this;
    }
    
    public ShowsSelection updatedAtNot(Long... value) {
        addNotEquals(ShowsColumns.UPDATED_AT, value);
        return this;
    }

    public ShowsSelection updatedAtGt(long value) {
        addGreaterThan(ShowsColumns.UPDATED_AT, value);
        return this;
    }

    public ShowsSelection updatedAtGtEq(long value) {
        addGreaterThanOrEquals(ShowsColumns.UPDATED_AT, value);
        return this;
    }

    public ShowsSelection updatedAtLt(long value) {
        addLessThan(ShowsColumns.UPDATED_AT, value);
        return this;
    }

    public ShowsSelection updatedAtLtEq(long value) {
        addLessThanOrEquals(ShowsColumns.UPDATED_AT, value);
        return this;
    }

    public ShowsSelection language(String... value) {
        addEquals(ShowsColumns.LANGUAGE, value);
        return this;
    }
    
    public ShowsSelection languageNot(String... value) {
        addNotEquals(ShowsColumns.LANGUAGE, value);
        return this;
    }


    public ShowsSelection json(String... value) {
        addEquals(ShowsColumns.JSON, value);
        return this;
    }
    
    public ShowsSelection jsonNot(String... value) {
        addNotEquals(ShowsColumns.JSON, value);
        return this;
    }

}
