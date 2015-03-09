package com.joss.voodootvdb.provider.movies_related;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movies_related} table.
 */
public class MoviesRelatedCursor extends AbstractCursor {
    public MoviesRelatedCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code movie_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getMovieTraktId() {
        return getIntegerOrNull(MoviesRelatedColumns.MOVIE_TRAKT_ID);
    }

    /**
     * Get the {@code related_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getRelatedTraktId() {
        return getIntegerOrNull(MoviesRelatedColumns.RELATED_TRAKT_ID);
    }
}
