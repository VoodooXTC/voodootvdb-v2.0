package com.joss.voodootvdb.provider.movies_people;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code movies_people} table.
 */
public interface MoviesPeopleColumns extends BaseColumns {
    String TABLE_NAME = "movies_people";
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