package com.joss.voodootvdb.provider.episodes_watched;

import android.content.Context;
import android.util.SparseIntArray;

import com.joss.voodootvdb.api.models.Progress.Episode;
import com.joss.voodootvdb.api.models.Progress.Season;
import com.joss.voodootvdb.api.models.Progress.Watched;
import com.joss.voodootvdb.model.EpisodesWatchedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/13/15
 * Time: 1:29 PM
 */
public class EpisodesWatchedProvider {

    public static List<EpisodesWatchedModel> get(int showTraktId, Watched watched) {
        List<EpisodesWatchedModel> items = new ArrayList<>();
        for(Season s : watched.getSeasons()){
            for(Episode e : s.getEpisodes()){
                items.add(new EpisodesWatchedModel(showTraktId, s.getNumber(), e));;
            }
        }
        return items;
    }

    private static List<EpisodesWatchedModel> get(EpisodesWatchedCursor cursor) {
        List<EpisodesWatchedModel> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                items.add(new EpisodesWatchedModel(cursor));
                cursor.moveToNext();
            }
        }
        return items;
    }

    public static int getWatchedCount(Context context, int showTraktId, Integer seasonNumber) {
        EpisodesWatchedSelection where = new EpisodesWatchedSelection();
        where.showTraktId(showTraktId).and().season(seasonNumber).and().completed(true);
        EpisodesWatchedCursor cursor = where.query(context.getContentResolver());
        List<EpisodesWatchedModel> episodes = get(cursor);
        cursor.close();
        return episodes.size();
    }

    public static SparseIntArray getProgress(EpisodesWatchedCursor watchedCursor) {
        SparseIntArray progress = new SparseIntArray();
        if(watchedCursor.moveToFirst()){
            while (!watchedCursor.isAfterLast()){
                progress.put(watchedCursor.getSeason(), progress.get(watchedCursor.getSeason()) + 1);
                watchedCursor.moveToNext();
            }
        }
        return progress;
    }
}
