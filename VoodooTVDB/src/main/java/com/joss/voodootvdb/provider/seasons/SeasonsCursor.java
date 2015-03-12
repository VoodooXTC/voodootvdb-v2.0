package com.joss.voodootvdb.provider.seasons;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code seasons} table.
 */
public class SeasonsCursor extends AbstractCursor {
    public SeasonsCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code show_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getShowTraktId() {
        return getIntegerOrNull(SeasonsColumns.SHOW_TRAKT_ID);
    }

    /**
     * Get the {@code season_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getSeasonTraktId() {
        return getIntegerOrNull(SeasonsColumns.SEASON_TRAKT_ID);
    }

    /**
     * Get the {@code number} value.
     * Can be {@code null}.
     */
    public Integer getNumber() {
        return getIntegerOrNull(SeasonsColumns.NUMBER);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(SeasonsColumns.JSON);
        return getString(index);
    }
}
