package com.joss.voodootvdb.provider.episodes_watched;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code episodes_watched} table.
 */
public class EpisodesWatchedCursor extends AbstractCursor {
    public EpisodesWatchedCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code show_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getShowTraktId() {
        return getIntegerOrNull(EpisodesWatchedColumns.SHOW_TRAKT_ID);
    }

    /**
     * Get the {@code season} value.
     * Can be {@code null}.
     */
    public Integer getSeason() {
        return getIntegerOrNull(EpisodesWatchedColumns.SEASON);
    }

    /**
     * Get the {@code number} value.
     * Can be {@code null}.
     */
    public Integer getNumber() {
        return getIntegerOrNull(EpisodesWatchedColumns.NUMBER);
    }

    /**
     * Get the {@code completed} value.
     * Can be {@code null}.
     */
    public Boolean getCompleted() {
        return getBoolean(EpisodesWatchedColumns.COMPLETED);
    }
}
