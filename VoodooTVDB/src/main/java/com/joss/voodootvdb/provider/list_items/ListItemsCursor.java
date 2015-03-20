package com.joss.voodootvdb.provider.list_items;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code list_items} table.
 */
public class ListItemsCursor extends AbstractCursor {
    public ListItemsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code list_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getListTraktId() {
        return getIntegerOrNull(ListItemsColumns.LIST_TRAKT_ID);
    }

    /**
     * Get the {@code listed_at} value.
     * Can be {@code null}.
     */
    public Long getListedAt() {
        return getLongOrNull(ListItemsColumns.LISTED_AT);
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    public String getType() {
        Integer index = getCachedColumnIndexOrThrow(ListItemsColumns.TYPE);
        return getString(index);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(ListItemsColumns.JSON);
        return getString(index);
    }
}
