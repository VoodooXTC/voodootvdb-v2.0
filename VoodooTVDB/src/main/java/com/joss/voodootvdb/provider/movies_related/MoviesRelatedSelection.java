package com.joss.voodootvdb.provider.movies_related;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code movies_related} table.
 */
public class MoviesRelatedSelection extends AbstractSelection<MoviesRelatedSelection> {
    @Override
    public Uri uri() {
        return MoviesRelatedColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code MoviesRelatedCursor} object, which is positioned before the first entry, or null.
     */
    public MoviesRelatedCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new MoviesRelatedCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public MoviesRelatedCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public MoviesRelatedCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public MoviesRelatedSelection id(long... value) {
        addEquals(MoviesRelatedColumns._ID, toObjectArray(value));
        return this;
    }

    public MoviesRelatedSelection movieTraktId(Integer... value) {
        addEquals(MoviesRelatedColumns.MOVIE_TRAKT_ID, value);
        return this;
    }
    
    public MoviesRelatedSelection movieTraktIdNot(Integer... value) {
        addNotEquals(MoviesRelatedColumns.MOVIE_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection movieTraktIdGt(int value) {
        addGreaterThan(MoviesRelatedColumns.MOVIE_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection movieTraktIdGtEq(int value) {
        addGreaterThanOrEquals(MoviesRelatedColumns.MOVIE_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection movieTraktIdLt(int value) {
        addLessThan(MoviesRelatedColumns.MOVIE_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection movieTraktIdLtEq(int value) {
        addLessThanOrEquals(MoviesRelatedColumns.MOVIE_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection relatedTraktId(Integer... value) {
        addEquals(MoviesRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }
    
    public MoviesRelatedSelection relatedTraktIdNot(Integer... value) {
        addNotEquals(MoviesRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection relatedTraktIdGt(int value) {
        addGreaterThan(MoviesRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection relatedTraktIdGtEq(int value) {
        addGreaterThanOrEquals(MoviesRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection relatedTraktIdLt(int value) {
        addLessThan(MoviesRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }

    public MoviesRelatedSelection relatedTraktIdLtEq(int value) {
        addLessThanOrEquals(MoviesRelatedColumns.RELATED_TRAKT_ID, value);
        return this;
    }
}
