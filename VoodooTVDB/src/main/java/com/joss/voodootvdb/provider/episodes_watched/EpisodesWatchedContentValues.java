package com.joss.voodootvdb.provider.episodes_watched;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.EpisodesWatchedModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code episodes_watched} table.
 */
public class EpisodesWatchedContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return EpisodesWatchedColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, EpisodesWatchedSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public EpisodesWatchedContentValues putShowTraktId(Integer value) {
        mContentValues.put(EpisodesWatchedColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesWatchedContentValues putShowTraktIdNull() {
        mContentValues.putNull(EpisodesWatchedColumns.SHOW_TRAKT_ID);
        return this;
    }


    public EpisodesWatchedContentValues putSeason(Integer value) {
        mContentValues.put(EpisodesWatchedColumns.SEASON, value);
        return this;
    }

    public EpisodesWatchedContentValues putSeasonNull() {
        mContentValues.putNull(EpisodesWatchedColumns.SEASON);
        return this;
    }


    public EpisodesWatchedContentValues putNumber(Integer value) {
        mContentValues.put(EpisodesWatchedColumns.NUMBER, value);
        return this;
    }

    public EpisodesWatchedContentValues putNumberNull() {
        mContentValues.putNull(EpisodesWatchedColumns.NUMBER);
        return this;
    }


    public EpisodesWatchedContentValues putCompleted(Boolean value) {
        mContentValues.put(EpisodesWatchedColumns.COMPLETED, value);
        return this;
    }

    public EpisodesWatchedContentValues putCompletedNull() {
        mContentValues.putNull(EpisodesWatchedColumns.COMPLETED);
        return this;
    }


    public static ContentValues[] getContentValues(List<EpisodesWatchedModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(EpisodesWatchedModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(EpisodesWatchedModel item){
        EpisodesWatchedContentValues values = new EpisodesWatchedContentValues();
        values.putShowTraktId(item.showTraktId);
        values.putSeason(item.season);
        values.putNumber(item.number);
        values.putCompleted(item.completed);
        return values.values();
    }
}
