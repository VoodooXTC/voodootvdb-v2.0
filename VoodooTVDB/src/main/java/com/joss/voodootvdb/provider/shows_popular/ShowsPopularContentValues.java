package com.joss.voodootvdb.provider.shows_popular;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.ShowsPopularModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code shows_popular} table.
 */
public class ShowsPopularContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ShowsPopularColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ShowsPopularSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ShowsPopularContentValues putTitle(String value) {
        mContentValues.put(ShowsPopularColumns.TITLE, value);
        return this;
    }

    public ShowsPopularContentValues putTitleNull() {
        mContentValues.putNull(ShowsPopularColumns.TITLE);
        return this;
    }


    public ShowsPopularContentValues putTraktId(Integer value) {
        mContentValues.put(ShowsPopularColumns.TRAKT_ID, value);
        return this;
    }

    public ShowsPopularContentValues putTraktIdNull() {
        mContentValues.putNull(ShowsPopularColumns.TRAKT_ID);
        return this;
    }


    public ShowsPopularContentValues putImdbId(String value) {
        mContentValues.put(ShowsPopularColumns.IMDB_ID, value);
        return this;
    }

    public ShowsPopularContentValues putImdbIdNull() {
        mContentValues.putNull(ShowsPopularColumns.IMDB_ID);
        return this;
    }


    public ShowsPopularContentValues putFirstAired(String value) {
        mContentValues.put(ShowsPopularColumns.FIRST_AIRED, value);
        return this;
    }

    public ShowsPopularContentValues putFirstAiredNull() {
        mContentValues.putNull(ShowsPopularColumns.FIRST_AIRED);
        return this;
    }


    public ShowsPopularContentValues putCountry(String value) {
        mContentValues.put(ShowsPopularColumns.COUNTRY, value);
        return this;
    }

    public ShowsPopularContentValues putCountryNull() {
        mContentValues.putNull(ShowsPopularColumns.COUNTRY);
        return this;
    }


    public ShowsPopularContentValues putStatus(String value) {
        mContentValues.put(ShowsPopularColumns.STATUS, value);
        return this;
    }

    public ShowsPopularContentValues putStatusNull() {
        mContentValues.putNull(ShowsPopularColumns.STATUS);
        return this;
    }


    public ShowsPopularContentValues putRating(Double value) {
        mContentValues.put(ShowsPopularColumns.RATING, value);
        return this;
    }

    public ShowsPopularContentValues putRatingNull() {
        mContentValues.putNull(ShowsPopularColumns.RATING);
        return this;
    }


    public ShowsPopularContentValues putUpdatedAt(String value) {
        mContentValues.put(ShowsPopularColumns.UPDATED_AT, value);
        return this;
    }

    public ShowsPopularContentValues putUpdatedAtNull() {
        mContentValues.putNull(ShowsPopularColumns.UPDATED_AT);
        return this;
    }


    public ShowsPopularContentValues putLanguage(String value) {
        mContentValues.put(ShowsPopularColumns.LANGUAGE, value);
        return this;
    }

    public ShowsPopularContentValues putLanguageNull() {
        mContentValues.putNull(ShowsPopularColumns.LANGUAGE);
        return this;
    }


    public ShowsPopularContentValues putJson(String value) {
        mContentValues.put(ShowsPopularColumns.JSON, value);
        return this;
    }

    public ShowsPopularContentValues putJsonNull() {
        mContentValues.putNull(ShowsPopularColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(List<ShowsPopularModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(ShowsPopularModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(ShowsPopularModel item){
        ShowsPopularContentValues values = new ShowsPopularContentValues();
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
