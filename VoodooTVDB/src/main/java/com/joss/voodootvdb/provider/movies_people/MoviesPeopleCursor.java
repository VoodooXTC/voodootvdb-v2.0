package com.joss.voodootvdb.provider.movies_people;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movies_people} table.
 */
public class MoviesPeopleCursor extends AbstractCursor {
    public MoviesPeopleCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(MoviesPeopleColumns.TRAKT_ID);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(MoviesPeopleColumns.JSON);
        return getString(index);
    }
}
