package com.joss.voodootvdb.provider.movies;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movies} table.
 */
public class MoviesCursor extends AbstractCursor {
    public MoviesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    public String getTitle() {
        Integer index = getCachedColumnIndexOrThrow(MoviesColumns.TITLE);
        return getString(index);
    }

    /**
     * Get the {@code year} value.
     * Can be {@code null}.
     */
    public Integer getYear() {
        return getIntegerOrNull(MoviesColumns.YEAR);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(MoviesColumns.TRAKT_ID);
    }

    /**
     * Get the {@code imdb_id} value.
     * Can be {@code null}.
     */
    public String getImdbId() {
        Integer index = getCachedColumnIndexOrThrow(MoviesColumns.IMDB_ID);
        return getString(index);
    }

    /**
     * Get the {@code released} value.
     * Can be {@code null}.
     */
    public Long getReleased() {
        return getLongOrNull(MoviesColumns.RELEASED);
    }

    /**
     * Get the {@code rating} value.
     * Can be {@code null}.
     */
    public Double getRating() {
        return getDoubleOrNull(MoviesColumns.RATING);
    }

    /**
     * Get the {@code updated_at} value.
     * Can be {@code null}.
     */
    public Long getUpdatedAt() {
        return getLongOrNull(MoviesColumns.UPDATED_AT);
    }

    /**
     * Get the {@code language} value.
     * Can be {@code null}.
     */
    public String getLanguage() {
        Integer index = getCachedColumnIndexOrThrow(MoviesColumns.LANGUAGE);
        return getString(index);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(MoviesColumns.JSON);
        return getString(index);
    }
}
