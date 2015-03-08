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

    public ShowsPopularSelection showTraktId(Integer... value) {
        addEquals(ShowsPopularColumns.SHOW_TRAKT_ID, value);
        return this;
    }
    
    public ShowsPopularSelection showTraktIdNot(Integer... value) {
        addNotEquals(ShowsPopularColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection showTraktIdGt(int value) {
        addGreaterThan(ShowsPopularColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection showTraktIdGtEq(int value) {
        addGreaterThanOrEquals(ShowsPopularColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection showTraktIdLt(int value) {
        addLessThan(ShowsPopularColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsPopularSelection showTraktIdLtEq(int value) {
        addLessThanOrEquals(ShowsPopularColumns.SHOW_TRAKT_ID, value);
        return this;
    }
}
