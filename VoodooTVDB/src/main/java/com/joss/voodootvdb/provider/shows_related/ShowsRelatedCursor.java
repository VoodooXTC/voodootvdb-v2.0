package com.joss.voodootvdb.provider.shows_related;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code shows_related} table.
 */
public class ShowsRelatedCursor extends AbstractCursor {
    public ShowsRelatedCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code show_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getShowTraktId() {
        return getIntegerOrNull(ShowsRelatedColumns.SHOW_TRAKT_ID);
    }

    /**
     * Get the {@code related_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getRelatedTraktId() {
        return getIntegerOrNull(ShowsRelatedColumns.RELATED_TRAKT_ID);
    }
}
