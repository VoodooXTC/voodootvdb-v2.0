package com.joss.voodootvdb.provider.shows_popular;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code shows_popular} table.
 */
public interface ShowsPopularColumns extends BaseColumns {
    String TABLE_NAME = "shows_popular";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String SHOW_TRAKT_ID = "show_trakt_id";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            SHOW_TRAKT_ID
    };
    // @formatter:on
}