package com.joss.voodootvdb.provider.seasons;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code seasons} table.
 */
public interface SeasonsColumns extends BaseColumns {
    String TABLE_NAME = "seasons";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String SHOW_TRAKT_ID = "show_trakt_id";
    String SEASON_TRAKT_ID = "season_trakt_id";
    String NUMBER = "number";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            SHOW_TRAKT_ID,
            SEASON_TRAKT_ID,
            NUMBER,
            JSON
    };
    // @formatter:on
}