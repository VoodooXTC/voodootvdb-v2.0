package com.joss.voodootvdb.provider.episodes_watched;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code episodes_watched} table.
 */
public interface EpisodesWatchedColumns extends BaseColumns {
    String TABLE_NAME = "episodes_watched";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String SHOW_TRAKT_ID = "show_trakt_id";
    String SEASON = "season";
    String NUMBER = "number";
    String COMPLETED = "completed";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            SHOW_TRAKT_ID,
            SEASON,
            NUMBER,
            COMPLETED
    };
    // @formatter:on
}