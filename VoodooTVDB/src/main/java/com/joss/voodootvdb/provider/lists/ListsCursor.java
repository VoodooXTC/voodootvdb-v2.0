package com.joss.voodootvdb.provider.lists;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code lists} table.
 */
public class ListsCursor extends AbstractCursor {
    public ListsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(ListsColumns.TRAKT_ID);
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    public String getName() {
        Integer index = getCachedColumnIndexOrThrow(ListsColumns.NAME);
        return getString(index);
    }

    /**
     * Get the {@code slug} value.
     * Can be {@code null}.
     */
    public String getSlug() {
        Integer index = getCachedColumnIndexOrThrow(ListsColumns.SLUG);
        return getString(index);
    }

    /**
     * Get the {@code updated_at} value.
     * Can be {@code null}.
     */
    public Long getUpdatedAt() {
        return getLongOrNull(ListsColumns.UPDATED_AT);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(ListsColumns.JSON);
        return getString(index);
    }
}
