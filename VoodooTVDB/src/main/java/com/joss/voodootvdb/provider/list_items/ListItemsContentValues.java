package com.joss.voodootvdb.provider.list_items;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.ListItemsModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code list_items} table.
 */
public class ListItemsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ListItemsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ListItemsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ListItemsContentValues putListTraktId(Integer value) {
        mContentValues.put(ListItemsColumns.LIST_TRAKT_ID, value);
        return this;
    }

    public ListItemsContentValues putListTraktIdNull() {
        mContentValues.putNull(ListItemsColumns.LIST_TRAKT_ID);
        return this;
    }


    public ListItemsContentValues putItemtraktid(Integer value) {
        mContentValues.put(ListItemsColumns.ITEMTRAKTID, value);
        return this;
    }

    public ListItemsContentValues putItemtraktidNull() {
        mContentValues.putNull(ListItemsColumns.ITEMTRAKTID);
        return this;
    }


    public ListItemsContentValues putListedAt(Long value) {
        mContentValues.put(ListItemsColumns.LISTED_AT, value);
        return this;
    }

    public ListItemsContentValues putListedAtNull() {
        mContentValues.putNull(ListItemsColumns.LISTED_AT);
        return this;
    }


    public ListItemsContentValues putType(String value) {
        mContentValues.put(ListItemsColumns.TYPE, value);
        return this;
    }

    public ListItemsContentValues putTypeNull() {
        mContentValues.putNull(ListItemsColumns.TYPE);
        return this;
    }


    public ListItemsContentValues putJson(String value) {
        mContentValues.put(ListItemsColumns.JSON, value);
        return this;
    }

    public ListItemsContentValues putJsonNull() {
        mContentValues.putNull(ListItemsColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(List<ListItemsModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(ListItemsModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(ListItemsModel item){
        ListItemsContentValues values = new ListItemsContentValues();
        values.putListTraktId(item.listTraktId);
        values.putItemtraktid(item.itemtraktid);
        values.putListedAt(item.listedAt);
        values.putType(item.type);
        values.putJson(item.json);
        return values.values();
    }
}
