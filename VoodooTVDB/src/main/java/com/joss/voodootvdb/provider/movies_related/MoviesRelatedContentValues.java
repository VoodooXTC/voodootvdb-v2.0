package com.joss.voodootvdb.provider.movies_related;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import com.joss.voodootvdb.model.MoviesRelatedModel;
import com.joss.voodootvdb.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movies_related} table.
 */
public class MoviesRelatedContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MoviesRelatedColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, MoviesRelatedSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MoviesRelatedContentValues putMovieTraktId(Integer value) {
        mContentValues.put(MoviesRelatedColumns.MOVIE_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedContentValues putMovieTraktIdNull() {
        mContentValues.putNull(MoviesRelatedColumns.MOVIE_TRAKT_ID);
        return this;
    }


    public MoviesRelatedContentValues putRelatedTraktId(Integer value) {
        mContentValues.put(MoviesRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedContentValues putRelatedTraktIdNull() {
        mContentValues.putNull(MoviesRelatedColumns.RELATED_TRAKT_ID);
        return this;
    }


    public static ContentValues[] getContentValues(List<MoviesRelatedModel> items){
        List<ContentValues> values = new ArrayList<ContentValues>();
        for(MoviesRelatedModel item : items){
            values.add(getSingleContentValue(item));
        }
        return values.toArray(new ContentValues[values.size()]);
    }

    public static ContentValues getSingleContentValue(MoviesRelatedModel item){
        MoviesRelatedContentValues values = new MoviesRelatedContentValues();
        values.putMovieTraktId(item.movieTraktId);
        values.putRelatedTraktId(item.relatedTraktId);
        return values.values();
    }
}
