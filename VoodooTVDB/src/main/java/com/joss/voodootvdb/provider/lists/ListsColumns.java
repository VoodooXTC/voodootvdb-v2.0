package com.joss.voodootvdb.provider.lists;

import android.net.Uri;
import android.provider.BaseColumns;

import com.joss.voodootvdb.provider.VoodooProvider;

/**
 * Columns for the {@code lists} table.
 */
public interface ListsColumns extends BaseColumns {
    String TABLE_NAME = "lists";
    Uri CONTENT_URI = Uri.parse(VoodooProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    String _ID = BaseColumns._ID;
    String TRAKT_ID = "trakt_id";
    String NAME = "name";
    String SLUG = "slug";
    String UPDATED_AT = "updated_at";
    String JSON = "json";

    String DEFAULT_ORDER = _ID;

	// @formatter:off
    String[] FULL_PROJECTION = new String[] {
            _ID,
            TRAKT_ID,
            NAME,
            SLUG,
            UPDATED_AT,
            JSON
    };
    // @formatter:on
}