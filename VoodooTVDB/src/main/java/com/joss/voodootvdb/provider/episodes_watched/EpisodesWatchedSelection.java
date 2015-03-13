package com.joss.voodootvdb.provider.episodes_watched;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code episodes_watched} table.
 */
public class EpisodesWatchedSelection extends AbstractSelection<EpisodesWatchedSelection> {
    @Override
    public Uri uri() {
        return EpisodesWatchedColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code EpisodesWatchedCursor} object, which is positioned before the first entry, or null.
     */
    public EpisodesWatchedCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new EpisodesWatchedCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public EpisodesWatchedCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public EpisodesWatchedCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public EpisodesWatchedSelection id(long... value) {
        addEquals(EpisodesWatchedColumns._ID, toObjectArray(value));
        return this;
    }

    public EpisodesWatchedSelection showTraktId(Integer... value) {
        addEquals(EpisodesWatchedColumns.SHOW_TRAKT_ID, value);
        return this;
    }
    
    public EpisodesWatchedSelection showTraktIdNot(Integer... value) {
        addNotEquals(EpisodesWatchedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesWatchedSelection showTraktIdGt(int value) {
        addGreaterThan(EpisodesWatchedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesWatchedSelection showTraktIdGtEq(int value) {
        addGreaterThanOrEquals(EpisodesWatchedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesWatchedSelection showTraktIdLt(int value) {
        addLessThan(EpisodesWatchedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesWatchedSelection showTraktIdLtEq(int value) {
        addLessThanOrEquals(EpisodesWatchedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesWatchedSelection season(Integer... value) {
        addEquals(EpisodesWatchedColumns.SEASON, value);
        return this;
    }
    
    public EpisodesWatchedSelection seasonNot(Integer... value) {
        addNotEquals(EpisodesWatchedColumns.SEASON, value);
        return this;
    }

    public EpisodesWatchedSelection seasonGt(int value) {
        addGreaterThan(EpisodesWatchedColumns.SEASON, value);
        return this;
    }

    public EpisodesWatchedSelection seasonGtEq(int value) {
        addGreaterThanOrEquals(EpisodesWatchedColumns.SEASON, value);
        return this;
    }

    public EpisodesWatchedSelection seasonLt(int value) {
        addLessThan(EpisodesWatchedColumns.SEASON, value);
        return this;
    }

    public EpisodesWatchedSelection seasonLtEq(int value) {
        addLessThanOrEquals(EpisodesWatchedColumns.SEASON, value);
        return this;
    }

    public EpisodesWatchedSelection number(Integer... value) {
        addEquals(EpisodesWatchedColumns.NUMBER, value);
        return this;
    }
    
    public EpisodesWatchedSelection numberNot(Integer... value) {
        addNotEquals(EpisodesWatchedColumns.NUMBER, value);
        return this;
    }

    public EpisodesWatchedSelection numberGt(int value) {
        addGreaterThan(EpisodesWatchedColumns.NUMBER, value);
        return this;
    }

    public EpisodesWatchedSelection numberGtEq(int value) {
        addGreaterThanOrEquals(EpisodesWatchedColumns.NUMBER, value);
        return this;
    }

    public EpisodesWatchedSelection numberLt(int value) {
        addLessThan(EpisodesWatchedColumns.NUMBER, value);
        return this;
    }

    public EpisodesWatchedSelection numberLtEq(int value) {
        addLessThanOrEquals(EpisodesWatchedColumns.NUMBER, value);
        return this;
    }

    public EpisodesWatchedSelection completed(Boolean... value) {
        addEquals(EpisodesWatchedColumns.COMPLETED, value);
        return this;
    }
    
    public EpisodesWatchedSelection completedNot(Boolean... value) {
        addNotEquals(EpisodesWatchedColumns.COMPLETED, value);
        return this;
    }

}
