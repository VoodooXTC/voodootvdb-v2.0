package com.joss.voodootvdb.provider.lists;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code lists} table.
 */
public class ListsSelection extends AbstractSelection<ListsSelection> {
    @Override
    public Uri uri() {
        return ListsColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ListsCursor} object, which is positioned before the first entry, or null.
     */
    public ListsCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ListsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ListsCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ListsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public ListsSelection id(long... value) {
        addEquals(ListsColumns._ID, toObjectArray(value));
        return this;
    }

    public ListsSelection traktId(Integer... value) {
        addEquals(ListsColumns.TRAKT_ID, value);
        return this;
    }
    
    public ListsSelection traktIdNot(Integer... value) {
        addNotEquals(ListsColumns.TRAKT_ID, value);
        return this;
    }

    public ListsSelection traktIdGt(int value) {
        addGreaterThan(ListsColumns.TRAKT_ID, value);
        return this;
    }

    public ListsSelection traktIdGtEq(int value) {
        addGreaterThanOrEquals(ListsColumns.TRAKT_ID, value);
        return this;
    }

    public ListsSelection traktIdLt(int value) {
        addLessThan(ListsColumns.TRAKT_ID, value);
        return this;
    }

    public ListsSelection traktIdLtEq(int value) {
        addLessThanOrEquals(ListsColumns.TRAKT_ID, value);
        return this;
    }

    public ListsSelection name(String... value) {
        addEquals(ListsColumns.NAME, value);
        return this;
    }
    
    public ListsSelection nameNot(String... value) {
        addNotEquals(ListsColumns.NAME, value);
        return this;
    }


    public ListsSelection slug(String... value) {
        addEquals(ListsColumns.SLUG, value);
        return this;
    }
    
    public ListsSelection slugNot(String... value) {
        addNotEquals(ListsColumns.SLUG, value);
        return this;
    }


    public ListsSelection updatedAt(Long... value) {
        addEquals(ListsColumns.UPDATED_AT, value);
        return this;
    }
    
    public ListsSelection updatedAtNot(Long... value) {
        addNotEquals(ListsColumns.UPDATED_AT, value);
        return this;
    }

    public ListsSelection updatedAtGt(long value) {
        addGreaterThan(ListsColumns.UPDATED_AT, value);
        return this;
    }

    public ListsSelection updatedAtGtEq(long value) {
        addGreaterThanOrEquals(ListsColumns.UPDATED_AT, value);
        return this;
    }

    public ListsSelection updatedAtLt(long value) {
        addLessThan(ListsColumns.UPDATED_AT, value);
        return this;
    }

    public ListsSelection updatedAtLtEq(long value) {
        addLessThanOrEquals(ListsColumns.UPDATED_AT, value);
        return this;
    }

    public ListsSelection json(String... value) {
        addEquals(ListsColumns.JSON, value);
        return this;
    }
    
    public ListsSelection jsonNot(String... value) {
        addNotEquals(ListsColumns.JSON, value);
        return this;
    }

}
