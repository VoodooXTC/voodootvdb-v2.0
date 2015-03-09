package com.joss.voodootvdb.provider.person_movies;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code person_movies} table.
 */
public interface PersonMoviesColumns extends BaseColumns {
    String TABLE_NAME = "person_movies";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String TRAKT_ID = "trakt_id";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            TRAKT_ID,
            JSON
    };
    // @formatter:on
}