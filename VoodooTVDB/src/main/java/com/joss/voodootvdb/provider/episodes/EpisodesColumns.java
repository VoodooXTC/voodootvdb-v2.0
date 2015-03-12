package com.joss.voodootvdb.provider.episodes;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code episodes} table.
 */
public interface EpisodesColumns extends BaseColumns {
    String TABLE_NAME = "episodes";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String SHOW_TRAKT_ID = "show_trakt_id";
    String EPISODE_TRAKT_ID = "episode_trakt_id";
    String SEASON = "season";
    String NUMBER = "number";
    String FIRST_AIRED = "first_aired";
    String UPDATED_AT = "updated_at";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            SHOW_TRAKT_ID,
            EPISODE_TRAKT_ID,
            SEASON,
            NUMBER,
            FIRST_AIRED,
            UPDATED_AT,
            JSON
    };
    // @formatter:on
}