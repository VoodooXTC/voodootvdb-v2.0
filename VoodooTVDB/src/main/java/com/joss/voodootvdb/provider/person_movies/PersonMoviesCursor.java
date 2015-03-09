package com.joss.voodootvdb.provider.person_movies;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code person_movies} table.
 */
public class PersonMoviesCursor extends AbstractCursor {
    public PersonMoviesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(PersonMoviesColumns.TRAKT_ID);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(PersonMoviesColumns.JSON);
        return getString(index);
    }
}
