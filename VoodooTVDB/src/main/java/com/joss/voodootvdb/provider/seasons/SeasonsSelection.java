package com.joss.voodootvdb.provider.seasons;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code seasons} table.
 */
public class SeasonsSelection extends AbstractSelection<SeasonsSelection> {
    @Override
    public Uri uri() {
        return SeasonsColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code SeasonsCursor} object, which is positioned before the first entry, or null.
     */
    public SeasonsCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new SeasonsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public SeasonsCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public SeasonsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public SeasonsSelection id(long... value) {
        addEquals(SeasonsColumns._ID, toObjectArray(value));
        return this;
    }

    public SeasonsSelection showTraktId(Integer... value) {
        addEquals(SeasonsColumns.SHOW_TRAKT_ID, value);
        return this;
    }
    
    public SeasonsSelection showTraktIdNot(Integer... value) {
        addNotEquals(SeasonsColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection showTraktIdGt(int value) {
        addGreaterThan(SeasonsColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection showTraktIdGtEq(int value) {
        addGreaterThanOrEquals(SeasonsColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection showTraktIdLt(int value) {
        addLessThan(SeasonsColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection showTraktIdLtEq(int value) {
        addLessThanOrEquals(SeasonsColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection seasonTraktId(Integer... value) {
        addEquals(SeasonsColumns.SEASON_TRAKT_ID, value);
        return this;
    }
    
    public SeasonsSelection seasonTraktIdNot(Integer... value) {
        addNotEquals(SeasonsColumns.SEASON_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection seasonTraktIdGt(int value) {
        addGreaterThan(SeasonsColumns.SEASON_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection seasonTraktIdGtEq(int value) {
        addGreaterThanOrEquals(SeasonsColumns.SEASON_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection seasonTraktIdLt(int value) {
        addLessThan(SeasonsColumns.SEASON_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection seasonTraktIdLtEq(int value) {
        addLessThanOrEquals(SeasonsColumns.SEASON_TRAKT_ID, value);
        return this;
    }

    public SeasonsSelection number(Integer... value) {
        addEquals(SeasonsColumns.NUMBER, value);
        return this;
    }
    
    public SeasonsSelection numberNot(Integer... value) {
        addNotEquals(SeasonsColumns.NUMBER, value);
        return this;
    }

    public SeasonsSelection numberGt(int value) {
        addGreaterThan(SeasonsColumns.NUMBER, value);
        return this;
    }

    public SeasonsSelection numberGtEq(int value) {
        addGreaterThanOrEquals(SeasonsColumns.NUMBER, value);
        return this;
    }

    public SeasonsSelection numberLt(int value) {
        addLessThan(SeasonsColumns.NUMBER, value);
        return this;
    }

    public SeasonsSelection numberLtEq(int value) {
        addLessThanOrEquals(SeasonsColumns.NUMBER, value);
        return this;
    }

    public SeasonsSelection json(String... value) {
        addEquals(SeasonsColumns.JSON, value);
        return this;
    }
    
    public SeasonsSelection jsonNot(String... value) {
        addNotEquals(SeasonsColumns.JSON, value);
        return this;
    }

}
