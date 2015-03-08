package com.joss.voodootvdb.provider.shows_related;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code shows_related} table.
 */
public class ShowsRelatedSelection extends AbstractSelection<ShowsRelatedSelection> {
    @Override
    public Uri uri() {
        return ShowsRelatedColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ShowsRelatedCursor} object, which is positioned before the first entry, or null.
     */
    public ShowsRelatedCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ShowsRelatedCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ShowsRelatedCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ShowsRelatedCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public ShowsRelatedSelection id(long... value) {
        addEquals(ShowsRelatedColumns._ID, toObjectArray(value));
        return this;
    }

    public ShowsRelatedSelection showTraktId(Integer... value) {
        addEquals(ShowsRelatedColumns.SHOW_TRAKT_ID, value);
        return this;
    }
    
    public ShowsRelatedSelection showTraktIdNot(Integer... value) {
        addNotEquals(ShowsRelatedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection showTraktIdGt(int value) {
        addGreaterThan(ShowsRelatedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection showTraktIdGtEq(int value) {
        addGreaterThanOrEquals(ShowsRelatedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection showTraktIdLt(int value) {
        addLessThan(ShowsRelatedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection showTraktIdLtEq(int value) {
        addLessThanOrEquals(ShowsRelatedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection relatedTraktId(Integer... value) {
        addEquals(ShowsRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }
    
    public ShowsRelatedSelection relatedTraktIdNot(Integer... value) {
        addNotEquals(ShowsRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection relatedTraktIdGt(int value) {
        addGreaterThan(ShowsRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection relatedTraktIdGtEq(int value) {
        addGreaterThanOrEquals(ShowsRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection relatedTraktIdLt(int value) {
        addLessThan(ShowsRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public ShowsRelatedSelection relatedTraktIdLtEq(int value) {
        addLessThanOrEquals(ShowsRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }
}
