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
    String TITLE = "title";
    String TRAKT_ID = "trakt_id";
    String IMDB_ID = "imdb_id";
    String FIRST_AIRED = "first_aired";
    String COUNTRY = "country";
    String STATUS = "status";
    String RATING = "rating";
    String UPDATED_AT = "updated_at";
    String LANGUAGE = "language";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            TITLE,
            TRAKT_ID,
            IMDB_ID,
            FIRST_AIRED,
            COUNTRY,
            STATUS,
            RATING,
            UPDATED_AT,
            LANGUAGE,
            JSON
    };
    // @formatter:on
}