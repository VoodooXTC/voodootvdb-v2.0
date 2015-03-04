package com.joss.voodootvdb.provider.shows_popular;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code shows_popular} table.
 */
public class ShowsPopularCursor extends AbstractCursor {
    public ShowsPopularCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    public String getTitle() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.TITLE);
        return getString(index);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(ShowsPopularColumns.TRAKT_ID);
    }

    /**
     * Get the {@code imdb_id} value.
     * Can be {@code null}.
     */
    public String getImdbId() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.IMDB_ID);
        return getString(index);
    }

    /**
     * Get the {@code first_aired} value.
     * Can be {@code null}.
     */
    public String getFirstAired() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.FIRST_AIRED);
        return getString(index);
    }

    /**
     * Get the {@code country} value.
     * Can be {@code null}.
     */
    public String getCountry() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.COUNTRY);
        return getString(index);
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    public String getStatus() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.STATUS);
        return getString(index);
    }

    /**
     * Get the {@code rating} value.
     * Can be {@code null}.
     */
    public Double getRating() {
        return getDoubleOrNull(ShowsPopularColumns.RATING);
    }

    /**
     * Get the {@code updated_at} value.
     * Can be {@code null}.
     */
    public String getUpdatedAt() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.UPDATED_AT);
        return getString(index);
    }

    /**
     * Get the {@code language} value.
     * Can be {@code null}.
     */
    public String getLanguage() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.LANGUAGE);
        return getString(index);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(ShowsPopularColumns.JSON);
        return getString(index);
    }
}
