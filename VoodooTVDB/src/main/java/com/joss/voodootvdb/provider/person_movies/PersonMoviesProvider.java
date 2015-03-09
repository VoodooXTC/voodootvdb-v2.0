package com.joss.voodootvdb.provider.person_movies;

import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 11:54 PM
 */
public class PersonMoviesProvider {
    public static People get(PersonMoviesCursor moviesCursor) {
        if(moviesCursor.moveToFirst()){
            return GGson.fromJson(moviesCursor.getJson(), People.class);
        }
        return null;
    }

    public static List<VoodooItem> get(int type, String sectionTitle, People peopleMovies) {
        List<VoodooItem> items = new ArrayList<>();
        for(Cast cast : peopleMovies.getCast()){
            cast.setType(type);
            cast.setSectionTitle(sectionTitle);
            items.add(cast);
        }
        return items;
    }
}
