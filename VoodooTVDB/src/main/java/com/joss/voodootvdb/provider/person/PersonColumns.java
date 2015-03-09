package com.joss.voodootvdb.provider.person;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code person} table.
 */
public interface PersonColumns extends BaseColumns {
    String TABLE_NAME = "person";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String NAME = "name";
    String TRAKT_ID = "trakt_id";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            NAME,
            TRAKT_ID,
            JSON
    };
    // @formatter:on
}