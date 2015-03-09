package com.joss.voodootvdb.provider.movies_related;

import android.content.Context;

import com.joss.voodootvdb.activities.MovieActivity;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.model.MoviesRelatedModel;
import com.joss.voodootvdb.provider.movies.MoviesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/9/15
 * Time: 1:42 PM
 */
public class MoviesRelatedProvider {
    public static List<MoviesRelatedModel> get(int traktId, List<Movie> moviesRelated) {
        List<MoviesRelatedModel> items = new ArrayList<>();
        for(Movie m : moviesRelated){
            items.add(new MoviesRelatedModel(traktId, m));
        }
        return items;
    }

    public static List<VoodooItem> getVoodooItems(Context context, int voodooCardViewType, MoviesRelatedCursor cursor) {
        List<VoodooItem> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Movie movie = MoviesProvider.get(context, cursor.getRelatedTraktId());
                if(movie != null) {
                    movie.setType(voodooCardViewType);
                    items.add(movie);
                }
                cursor.moveToNext();
            }
        }
        return items;
    }
}
