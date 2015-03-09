package com.joss.voodootvdb.provider.person_shows;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code person_shows} table.
 */
public class PersonShowsCursor extends AbstractCursor {
    public PersonShowsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(PersonShowsColumns.TRAKT_ID);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(PersonShowsColumns.JSON);
        return getString(index);
    }
}
