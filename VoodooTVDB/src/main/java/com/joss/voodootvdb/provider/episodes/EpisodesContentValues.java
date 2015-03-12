package com.joss.voodootvdb.provider.episodes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.EpisodesModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code episodes} table.
 */
public class EpisodesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return EpisodesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, EpisodesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public EpisodesContentValues putShowTraktId(Integer value) {
        mContentValues.put(EpisodesColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesContentValues putShowTraktIdNull() {
        mContentValues.putNull(EpisodesColumns.SHOW_TRAKT_ID);
        return this;
    }


    public EpisodesContentValues putEpisodeTraktId(Integer value) {
        mContentValues.put(EpisodesColumns.EPISODE_TRAKT_ID, value);
        return this;
    }

    public EpisodesContentValues putEpisodeTraktIdNull() {
        mContentValues.putNull(EpisodesColumns.EPISODE_TRAKT_ID);
        return this;
    }


    public EpisodesContentValues putSeason(Integer value) {
        mContentValues.put(EpisodesColumns.SEASON, value);
        return this;
    }

    public EpisodesContentValues putSeasonNull() {
        mContentValues.putNull(EpisodesColumns.SEASON);
        return this;
    }


    public EpisodesContentValues putNumber(Integer value) {
        mContentValues.put(EpisodesColumns.NUMBER, value);
        return this;
    }

    public EpisodesContentValues putNumberNull() {
        mContentValues.putNull(EpisodesColumns.NUMBER);
        return this;
    }


    public EpisodesContentValues putFirstAired(String value) {
        mContentValues.put(EpisodesColumns.FIRST_AIRED, value);
        return this;
    }

    public EpisodesContentValues putFirstAiredNull() {
        mContentValues.putNull(EpisodesColumns.FIRST_AIRED);
        return this;
    }


    public EpisodesContentValues putUpdatedAt(String value) {
        mContentValues.put(EpisodesColumns.UPDATED_AT, value);
        return this;
    }

    public EpisodesContentValues putUpdatedAtNull() {
        mContentValues.putNull(EpisodesColumns.UPDATED_AT);
        return this;
    }


    public EpisodesContentValues putJson(String value) {
        mContentValues.put(EpisodesColumns.JSON, value);
        return this;
    }

    public EpisodesContentValues putJsonNull() {
        mContentValues.putNull(EpisodesColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(List<EpisodesModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(EpisodesModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(EpisodesModel item){
        EpisodesContentValues values = new EpisodesContentValues();
        values.putShowTraktId(item.showTraktId);
        values.putEpisodeTraktId(item.episodeTraktId);
        values.putSeason(item.season);
        values.putNumber(item.number);
        values.putFirstAired(item.firstAired);
        values.putUpdatedAt(item.updatedAt);
        values.putJson(item.json);
        return values.values();
    }
}
