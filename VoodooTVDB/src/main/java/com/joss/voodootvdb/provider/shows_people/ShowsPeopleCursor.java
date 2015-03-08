package com.joss.voodootvdb.provider.shows_people;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code shows_people} table.
 */
public class ShowsPeopleCursor extends AbstractCursor {
    public ShowsPeopleCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(ShowsPeopleColumns.TRAKT_ID);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPeopleColumns.JSON);
        return getString(index);
    }
}
