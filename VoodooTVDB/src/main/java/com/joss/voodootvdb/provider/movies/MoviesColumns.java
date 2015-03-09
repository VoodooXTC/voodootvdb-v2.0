package com.joss.voodootvdb.provider.movies;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code movies} table.
 */
public interface MoviesColumns extends BaseColumns {
    String TABLE_NAME = "movies";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String TITLE = "title";
    String YEAR = "year";
    String TRAKT_ID = "trakt_id";
    String IMDB_ID = "imdb_id";
    String RELEASED = "released";
    String RATING = "rating";
    String UPDATED_AT = "updated_at";
    String LANGUAGE = "language";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            TITLE,
            YEAR,
            TRAKT_ID,
            IMDB_ID,
            RELEASED,
            RATING,
            UPDATED_AT,
            LANGUAGE,
            JSON
    };
    // @formatter:on
}