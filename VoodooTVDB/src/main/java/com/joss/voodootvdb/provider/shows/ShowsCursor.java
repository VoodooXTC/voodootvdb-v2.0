package com.joss.voodootvdb.provider.shows;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code shows} table.
 */
public class ShowsCursor extends AbstractCursor {
    public ShowsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    public String getTitle() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.TITLE);
        return getString(index);
    }

    /**
     * Get the {@code trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getTraktId() {
        return getIntegerOrNull(ShowsColumns.TRAKT_ID);
    }

    /**
     * Get the {@code imdb_id} value.
     * Can be {@code null}.
     */
    public String getImdbId() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.IMDB_ID);
        return getString(index);
    }

    /**
     * Get the {@code first_aired} value.
     * Can be {@code null}.
     */
    public String getFirstAired() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.FIRST_AIRED);
        return getString(index);
    }

    /**
     * Get the {@code country} value.
     * Can be {@code null}.
     */
    public String getCountry() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.COUNTRY);
        return getString(index);
    }

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    public String getStatus() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.STATUS);
        return getString(index);
    }

    /**
     * Get the {@code rating} value.
     * Can be {@code null}.
     */
    public Double getRating() {
        return getDoubleOrNull(ShowsColumns.RATING);
    }

    /**
     * Get the {@code updated_at} value.
     * Can be {@code null}.
     */
    public String getUpdatedAt() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.UPDATED_AT);
        return getString(index);
    }

    /**
     * Get the {@code language} value.
     * Can be {@code null}.
     */
    public String getLanguage() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.LANGUAGE);
        return getString(index);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(ShowsColumns.JSON);
        return getString(index);
    }
}
