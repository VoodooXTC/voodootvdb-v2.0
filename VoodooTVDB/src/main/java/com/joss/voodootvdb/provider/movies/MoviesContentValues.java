package com.joss.voodootvdb.provider.movies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.MoviesModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movies} table.
 */
public class MoviesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MoviesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, MoviesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MoviesContentValues putTitle(String value) {
        mContentValues.put(MoviesColumns.TITLE, value);
        return this;
    }

    public MoviesContentValues putTitleNull() {
        mContentValues.putNull(MoviesColumns.TITLE);
        return this;
    }


    public MoviesContentValues putYear(Integer value) {
        mContentValues.put(MoviesColumns.YEAR, value);
        return this;
    }

    public MoviesContentValues putYearNull() {
        mContentValues.putNull(MoviesColumns.YEAR);
        return this;
    }


    public MoviesContentValues putTraktId(Integer value) {
        mContentValues.put(MoviesColumns.TRAKT_ID, value);
        return this;
    }

    public MoviesContentValues putTraktIdNull() {
        mContentValues.putNull(MoviesColumns.TRAKT_ID);
        return this;
    }


    public MoviesContentValues putImdbId(String value) {
        mContentValues.put(MoviesColumns.IMDB_ID, value);
        return this;
    }

    public MoviesContentValues putImdbIdNull() {
        mContentValues.putNull(MoviesColumns.IMDB_ID);
        return this;
    }


    public MoviesContentValues putReleased(Long value) {
        mContentValues.put(MoviesColumns.RELEASED, value);
        return this;
    }

    public MoviesContentValues putReleasedNull() {
        mContentValues.putNull(MoviesColumns.RELEASED);
        return this;
    }


    public MoviesContentValues putRating(Double value) {
        mContentValues.put(MoviesColumns.RATING, value);
        return this;
    }

    public MoviesContentValues putRatingNull() {
        mContentValues.putNull(MoviesColumns.RATING);
        return this;
    }


    public MoviesContentValues putUpdatedAt(Long value) {
        mContentValues.put(MoviesColumns.UPDATED_AT, value);
        return this;
    }

    public MoviesContentValues putUpdatedAtNull() {
        mContentValues.putNull(MoviesColumns.UPDATED_AT);
        return this;
    }


    public MoviesContentValues putLanguage(String value) {
        mContentValues.put(MoviesColumns.LANGUAGE, value);
        return this;
    }

    public MoviesContentValues putLanguageNull() {
        mContentValues.putNull(MoviesColumns.LANGUAGE);
        return this;
    }


    public MoviesContentValues putJson(String value) {
        mContentValues.put(MoviesColumns.JSON, value);
        return this;
    }

    public MoviesContentValues putJsonNull() {
        mContentValues.putNull(MoviesColumns.JSON);
        return this;
    }


    public static ContentValues[] getContentValues(List<MoviesModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(MoviesModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(MoviesModel item){
        MoviesContentValues values = new MoviesContentValues();
        values.putTitle(item.title);
        values.putYear(item.year);
        values.putTraktId(item.traktId);
        values.putImdbId(item.imdbId);
        values.putReleased(item.released);
        values.putRating(item.rating);
        values.putUpdatedAt(item.updatedAt);
        values.putLanguage(item.language);
        values.putJson(item.json);
        return values.values();
    }
}
