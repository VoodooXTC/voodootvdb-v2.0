package com.joss.voodootvdb.provider.list_items;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code list_items} table.
 */
public class ListItemsSelection extends AbstractSelection<ListItemsSelection> {
    @Override
    public Uri uri() {
        return ListItemsColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ListItemsCursor} object, which is positioned before the first entry, or null.
     */
    public ListItemsCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ListItemsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ListItemsCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ListItemsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public ListItemsSelection id(long... value) {
        addEquals(ListItemsColumns._ID, toObjectArray(value));
        return this;
    }

    public ListItemsSelection listTraktId(Integer... value) {
        addEquals(ListItemsColumns.LIST_TRAKT_ID, value);
        return this;
    }
    
    public ListItemsSelection listTraktIdNot(Integer... value) {
        addNotEquals(ListItemsColumns.LIST_TRAKT_ID, value);
        return this;
    }

    public ListItemsSelection listTraktIdGt(int value) {
        addGreaterThan(ListItemsColumns.LIST_TRAKT_ID, value);
        return this;
    }

    public ListItemsSelection listTraktIdGtEq(int value) {
        addGreaterThanOrEquals(ListItemsColumns.LIST_TRAKT_ID, value);
        return this;
    }

    public ListItemsSelection listTraktIdLt(int value) {
        addLessThan(ListItemsColumns.LIST_TRAKT_ID, value);
        return this;
    }

    public ListItemsSelection listTraktIdLtEq(int value) {
        addLessThanOrEquals(ListItemsColumns.LIST_TRAKT_ID, value);
        return this;
    }

    public ListItemsSelection listedAt(Long... value) {
        addEquals(ListItemsColumns.LISTED_AT, value);
        return this;
    }
    
    public ListItemsSelection listedAtNot(Long... value) {
        addNotEquals(ListItemsColumns.LISTED_AT, value);
        return this;
    }

    public ListItemsSelection listedAtGt(long value) {
        addGreaterThan(ListItemsColumns.LISTED_AT, value);
        return this;
    }

    public ListItemsSelection listedAtGtEq(long value) {
        addGreaterThanOrEquals(ListItemsColumns.LISTED_AT, value);
        return this;
    }

    public ListItemsSelection listedAtLt(long value) {
        addLessThan(ListItemsColumns.LISTED_AT, value);
        return this;
    }

    public ListItemsSelection listedAtLtEq(long value) {
        addLessThanOrEquals(ListItemsColumns.LISTED_AT, value);
        return this;
    }

    public ListItemsSelection type(String... value) {
        addEquals(ListItemsColumns.TYPE, value);
        return this;
    }
    
    public ListItemsSelection typeNot(String... value) {
        addNotEquals(ListItemsColumns.TYPE, value);
        return this;
    }


    public ListItemsSelection json(String... value) {
        addEquals(ListItemsColumns.JSON, value);
        return this;
    }
    
    public ListItemsSelection jsonNot(String... value) {
        addNotEquals(ListItemsColumns.JSON, value);
        return this;
    }

}
