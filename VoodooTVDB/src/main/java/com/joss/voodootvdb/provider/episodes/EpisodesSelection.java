package com.joss.voodootvdb.provider.episodes;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.joss.voodootvdb.provider.base.AbstractSelection;

/**
 * Selection for the {@code episodes} table.
 */
public class EpisodesSelection extends AbstractSelection<EpisodesSelection> {
    @Override
    public Uri uri() {
        return EpisodesColumns.CONTENT_URI;
    }
    
    /**
     * Query the given content resolver using this selection.
     * 
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code EpisodesCursor} object, which is positioned before the first entry, or null.
     */
    public EpisodesCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new EpisodesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public EpisodesCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public EpisodesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }
    
    
    public EpisodesSelection id(long... value) {
        addEquals(EpisodesColumns._ID, toObjectArray(value));
        return this;
    }

    public EpisodesSelection showTraktId(Integer... value) {
        addEquals(EpisodesColumns.SHOW_TRAKT_ID, value);
        return this;
    }
    
    public EpisodesSelection showTraktIdNot(Integer... value) {
        addNotEquals(EpisodesColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection showTraktIdGt(int value) {
        addGreaterThan(EpisodesColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection showTraktIdGtEq(int value) {
        addGreaterThanOrEquals(EpisodesColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection showTraktIdLt(int value) {
        addLessThan(EpisodesColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection showTraktIdLtEq(int value) {
        addLessThanOrEquals(EpisodesColumns.SHOW_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection episodeTraktId(Integer... value) {
        addEquals(EpisodesColumns.EPISODE_TRAKT_ID, value);
        return this;
    }
    
    public EpisodesSelection episodeTraktIdNot(Integer... value) {
        addNotEquals(EpisodesColumns.EPISODE_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection episodeTraktIdGt(int value) {
        addGreaterThan(EpisodesColumns.EPISODE_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection episodeTraktIdGtEq(int value) {
        addGreaterThanOrEquals(EpisodesColumns.EPISODE_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection episodeTraktIdLt(int value) {
        addLessThan(EpisodesColumns.EPISODE_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection episodeTraktIdLtEq(int value) {
        addLessThanOrEquals(EpisodesColumns.EPISODE_TRAKT_ID, value);
        return this;
    }

    public EpisodesSelection season(Integer... value) {
        addEquals(EpisodesColumns.SEASON, value);
        return this;
    }
    
    public EpisodesSelection seasonNot(Integer... value) {
        addNotEquals(EpisodesColumns.SEASON, value);
        return this;
    }

    public EpisodesSelection seasonGt(int value) {
        addGreaterThan(EpisodesColumns.SEASON, value);
        return this;
    }

    public EpisodesSelection seasonGtEq(int value) {
        addGreaterThanOrEquals(EpisodesColumns.SEASON, value);
        return this;
    }

    public EpisodesSelection seasonLt(int value) {
        addLessThan(EpisodesColumns.SEASON, value);
        return this;
    }

    public EpisodesSelection seasonLtEq(int value) {
        addLessThanOrEquals(EpisodesColumns.SEASON, value);
        return this;
    }

    public EpisodesSelection number(Integer... value) {
        addEquals(EpisodesColumns.NUMBER, value);
        return this;
    }
    
    public EpisodesSelection numberNot(Integer... value) {
        addNotEquals(EpisodesColumns.NUMBER, value);
        return this;
    }

    public EpisodesSelection numberGt(int value) {
        addGreaterThan(EpisodesColumns.NUMBER, value);
        return this;
    }

    public EpisodesSelection numberGtEq(int value) {
        addGreaterThanOrEquals(EpisodesColumns.NUMBER, value);
        return this;
    }

    public EpisodesSelection numberLt(int value) {
        addLessThan(EpisodesColumns.NUMBER, value);
        return this;
    }

    public EpisodesSelection numberLtEq(int value) {
        addLessThanOrEquals(EpisodesColumns.NUMBER, value);
        return this;
    }

    public EpisodesSelection firstAired(Long... value) {
        addEquals(EpisodesColumns.FIRST_AIRED, value);
        return this;
    }
    
    public EpisodesSelection firstAiredNot(Long... value) {
        addNotEquals(EpisodesColumns.FIRST_AIRED, value);
        return this;
    }

    public EpisodesSelection firstAiredGt(long value) {
        addGreaterThan(EpisodesColumns.FIRST_AIRED, value);
        return this;
    }

    public EpisodesSelection firstAiredGtEq(long value) {
        addGreaterThanOrEquals(EpisodesColumns.FIRST_AIRED, value);
        return this;
    }

    public EpisodesSelection firstAiredLt(long value) {
        addLessThan(EpisodesColumns.FIRST_AIRED, value);
        return this;
    }

    public EpisodesSelection firstAiredLtEq(long value) {
        addLessThanOrEquals(EpisodesColumns.FIRST_AIRED, value);
        return this;
    }

    public EpisodesSelection updatedAt(Long... value) {
        addEquals(EpisodesColumns.UPDATED_AT, value);
        return this;
    }
    
    public EpisodesSelection updatedAtNot(Long... value) {
        addNotEquals(EpisodesColumns.UPDATED_AT, value);
        return this;
    }

    public EpisodesSelection updatedAtGt(long value) {
        addGreaterThan(EpisodesColumns.UPDATED_AT, value);
        return this;
    }

    public EpisodesSelection updatedAtGtEq(long value) {
        addGreaterThanOrEquals(EpisodesColumns.UPDATED_AT, value);
        return this;
    }

    public EpisodesSelection updatedAtLt(long value) {
        addLessThan(EpisodesColumns.UPDATED_AT, value);
        return this;
    }

    public EpisodesSelection updatedAtLtEq(long value) {
        addLessThanOrEquals(EpisodesColumns.UPDATED_AT, value);
        return this;
    }

    public EpisodesSelection json(String... value) {
        addEquals(EpisodesColumns.JSON, value);
        return this;
    }
    
    public EpisodesSelection jsonNot(String... value) {
        addNotEquals(EpisodesColumns.JSON, value);
        return this;
    }

}
