package com.joss.voodootvdb.provider.movies;

import android.content.Context;
import android.database.Cursor;

import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.model.MoviesModel;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/9/15
 * Time: 1:23 PM
 */
public class MoviesProvider {

    public static List<MoviesModel> get(List<Movie> moviesRelated) {
        List<MoviesModel> items = new ArrayList<>();
        for(Movie movie : moviesRelated){
            items.add(new MoviesModel(movie));
        }
        return items;
    }

    public static Movie get(MoviesCursor cursor) {
        if(cursor.moveToFirst()){
            return GGson.fromJson(cursor.getJson(), Movie.class);
        }
        return null;
    }

    public static Movie get(Context context, Integer traktId) {
        Movie movie = null;

        MoviesSelection where = new MoviesSelection();
        where.traktId(traktId);

        Cursor cursor = context.getContentResolver().query(MoviesColumns.CONTENT_URI, MoviesColumns.FULL_PROJECTION, where.sel(), where.args(), null);
        MoviesCursor moviesCursor = new MoviesCursor(cursor);

        if(moviesCursor.moveToFirst()){
            movie = GGson.fromJson(moviesCursor.getJson(), Movie.class);
        }

        cursor.close();
        moviesCursor.close();

        return movie;
    }
}
