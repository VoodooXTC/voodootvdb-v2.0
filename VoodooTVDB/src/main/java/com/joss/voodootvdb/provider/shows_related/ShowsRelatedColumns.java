package com.joss.voodootvdb.provider.shows_related;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code shows_related} table.
 */
public interface ShowsRelatedColumns extends BaseColumns {
    String TABLE_NAME = "shows_related";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String SHOW_TRAKT_ID = "show_trakt_id";
    String RELATED_TRAKT_ID = "related_trakt_id";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            SHOW_TRAKT_ID,
            RELATED_TRAKT_ID
    };
    // @formatter:on
}