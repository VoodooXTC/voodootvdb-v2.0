package com.joss.voodootvdb.provider.seasons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.SeasonsModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code seasons} table.
 */
public class SeasonsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return SeasonsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, SeasonsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public SeasonsContentValues putShowTraktId(Integer value) {
        mContentValues.put(SeasonsColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public SeasonsContentValues putShowTraktIdNull() {
        mContentValues.putNull(SeasonsColumns.SHOW_TRAKT_ID);
        return this;
    }


    public SeasonsContentValues putSeasonTraktId(Integer value) {
        mContentValues.put(SeasonsColumns.SEASON_TRAKT_ID, value);
        return this;
    }

    public SeasonsContentValues putSeasonTraktIdNull() {
        mContentValues.putNull(SeasonsColumns.SEASON_TRAKT_ID);
        return this;
    }


    public SeasonsContentValues putNumber(Integer value) {
        mContentValues.put(SeasonsColumns.NUMBER, value);
        return this;
    }

    public SeasonsContentValues putNumberNull() {
        mContentValues.putNull(SeasonsColumns.NUMBER);
        return this;
    }


    public SeasonsContentValues putJson(String value) {
        mContentValues.put(SeasonsColumns.JSON, value);
        return this;
    }

    public SeasonsContentValues putJsonNull() {
        mContentValues.putNull(SeasonsColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(List<SeasonsModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(SeasonsModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(SeasonsModel item){
        SeasonsContentValues values = new SeasonsContentValues();
        values.putShowTraktId(item.showTraktId);
        values.putSeasonTraktId(item.seasonTraktId);
        values.putNumber(item.number);
        values.putJson(item.json);
        return values.values();
    }
}
