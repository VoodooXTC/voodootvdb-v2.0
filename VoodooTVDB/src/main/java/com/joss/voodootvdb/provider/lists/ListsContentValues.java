package com.joss.voodootvdb.provider.lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.ListsModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code lists} table.
 */
public class ListsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ListsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ListsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ListsContentValues putTraktId(Integer value) {
        mContentValues.put(ListsColumns.TRAKT_ID, value);
        return this;
    }

    public ListsContentValues putTraktIdNull() {
        mContentValues.putNull(ListsColumns.TRAKT_ID);
        return this;
    }


    public ListsContentValues putName(String value) {
        mContentValues.put(ListsColumns.NAME, value);
        return this;
    }

    public ListsContentValues putNameNull() {
        mContentValues.putNull(ListsColumns.NAME);
        return this;
    }


    public ListsContentValues putSlug(String value) {
        mContentValues.put(ListsColumns.SLUG, value);
        return this;
    }

    public ListsContentValues putSlugNull() {
        mContentValues.putNull(ListsColumns.SLUG);
        return this;
    }


    public ListsContentValues putUpdatedAt(Long value) {
        mContentValues.put(ListsColumns.UPDATED_AT, value);
        return this;
    }

    public ListsContentValues putUpdatedAtNull() {
        mContentValues.putNull(ListsColumns.UPDATED_AT);
        return this;
    }


    public ListsContentValues putJson(String value) {
        mContentValues.put(ListsColumns.JSON, value);
        return this;
    }

    public ListsContentValues putJsonNull() {
        mContentValues.putNull(ListsColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(List<ListsModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(ListsModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(ListsModel item){
        ListsContentValues values = new ListsContentValues();
        values.putTraktId(item.traktId);
        values.putName(item.name);
        values.putSlug(item.slug);
        values.putUpdatedAt(item.updatedAt);
        values.putJson(item.json);
        return values.values();
    }
}
