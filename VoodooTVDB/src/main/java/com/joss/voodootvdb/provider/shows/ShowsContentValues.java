package com.joss.voodootvdb.provider.shows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.ShowsModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code shows} table.
 */
public class ShowsContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ShowsColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ShowsSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ShowsContentValues putTitle(String value) {
        mContentValues.put(ShowsColumns.TITLE, value);
        return this;
    }

    public ShowsContentValues putTitleNull() {
        mContentValues.putNull(ShowsColumns.TITLE);
        return this;
    }


    public ShowsContentValues putTraktId(Integer value) {
        mContentValues.put(ShowsColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsContentValues putTraktIdNull() {
        mContentValues.putNull(ShowsColumns.TRAKT_ID);
        return this;
    }


    public ShowsContentValues putImdbId(String value) {
        mContentValues.put(ShowsColumns.IMDB_ID, value);
        return this;
    }

    public ShowsContentValues putImdbIdNull() {
        mContentValues.putNull(ShowsColumns.IMDB_ID);
        return this;
    }


    public ShowsContentValues putFirstAired(String value) {
        mContentValues.put(ShowsColumns.FIRST_AIRED, value);
        return this;
    }

    public ShowsContentValues putFirstAiredNull() {
        mContentValues.putNull(ShowsColumns.FIRST_AIRED);
        return this;
    }


    public ShowsContentValues putCountry(String value) {
        mContentValues.put(ShowsColumns.COUNTRY, value);
        return this;
    }

    public ShowsContentValues putCountryNull() {
        mContentValues.putNull(ShowsColumns.COUNTRY);
        return this;
    }


    public ShowsContentValues putStatus(String value) {
        mContentValues.put(ShowsColumns.STATUS, value);
        return this;
    }

    public ShowsContentValues putStatusNull() {
        mContentValues.putNull(ShowsColumns.STATUS);
        return this;
    }


    public ShowsContentValues putRating(Double value) {
        mContentValues.put(ShowsColumns.RATING, value);
        return this;
    }

    public ShowsContentValues putRatingNull() {
        mContentValues.putNull(ShowsColumns.RATING);
        return this;
    }


    public ShowsContentValues putUpdatedAt(String value) {
        mContentValues.put(ShowsColumns.UPDATED_AT, value);
        return this;
    }

    public ShowsContentValues putUpdatedAtNull() {
        mContentValues.putNull(ShowsColumns.UPDATED_AT);
        return this;
    }


    public ShowsContentValues putLanguage(String value) {
        mContentValues.put(ShowsColumns.LANGUAGE, value);
        return this;
    }

    public ShowsContentValues putLanguageNull() {
        mContentValues.putNull(ShowsColumns.LANGUAGE);
        return this;
    }


    public ShowsContentValues putJson(String value) {
        mContentValues.put(ShowsColumns.JSON, value);
        return this;
    }

    public ShowsContentValues putJsonNull() {
        mContentValues.putNull(ShowsColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(ShowsModel... items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(ShowsModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(ShowsModel item){
        ShowsContentValues values = new ShowsContentValues();
        values.putTitle(item.title);
        values.putTraktId(item.traktId);
        values.putImdbId(item.imdbId);
        values.putFirstAired(item.firstAired);
        values.putCountry(item.country);
        values.putStatus(item.status);
        values.putRating(item.rating);
        values.putUpdatedAt(item.updatedAt);
        values.putLanguage(item.language);
        values.putJson(item.json);
        return values.values();
    }
}
