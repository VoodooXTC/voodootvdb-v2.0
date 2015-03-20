package com.joss.voodootvdb.provider.list_items;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code list_items} table.
 */
public interface ListItemsColumns extends BaseColumns {
    String TABLE_NAME = "list_items";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String LIST_TRAKT_ID = "list_trakt_id";
    String LISTED_AT = "listed_at";
    String TYPE = "type";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            LIST_TRAKT_ID,
            LISTED_AT,
            TYPE,
            JSON
    };
    // @formatter:on
}