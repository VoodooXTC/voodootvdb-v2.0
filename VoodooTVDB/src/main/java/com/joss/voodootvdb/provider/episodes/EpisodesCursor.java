package com.joss.voodootvdb.provider.episodes;

import java.util.Date;

import android.database.Cursor;

import com.joss.voodootvdb.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code episodes} table.
 */
public class EpisodesCursor extends AbstractCursor {
    public EpisodesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code show_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getShowTraktId() {
        return getIntegerOrNull(EpisodesColumns.SHOW_TRAKT_ID);
    }

    /**
     * Get the {@code episode_trakt_id} value.
     * Can be {@code null}.
     */
    public Integer getEpisodeTraktId() {
        return getIntegerOrNull(EpisodesColumns.EPISODE_TRAKT_ID);
    }

    /**
     * Get the {@code season} value.
     * Can be {@code null}.
     */
    public Integer getSeason() {
        return getIntegerOrNull(EpisodesColumns.SEASON);
    }

    /**
     * Get the {@code number} value.
     * Can be {@code null}.
     */
    public Integer getNumber() {
        return getIntegerOrNull(EpisodesColumns.NUMBER);
    }

    /**
     * Get the {@code first_aired} value.
     * Can be {@code null}.
     */
    public Long getFirstAired() {
        return getLongOrNull(EpisodesColumns.FIRST_AIRED);
    }

    /**
     * Get the {@code updated_at} value.
     * Can be {@code null}.
     */
    public Long getUpdatedAt() {
        return getLongOrNull(EpisodesColumns.UPDATED_AT);
    }

    /**
     * Get the {@code json} value.
     * Can be {@code null}.
     */
    public String getJson() {
        Integer index = getCachedColumnIndexOrThrow(EpisodesColumns.JSON);
        return getString(index);
    }
}
