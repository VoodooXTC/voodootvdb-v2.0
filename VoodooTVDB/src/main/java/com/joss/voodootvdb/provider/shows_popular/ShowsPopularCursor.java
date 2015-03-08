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
     * Get the {@code show_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getShowTraktId() {
        return getIntegerOrNull(ShowsPopularColumns.SHOW_TRAKT_ID);
    }
}
