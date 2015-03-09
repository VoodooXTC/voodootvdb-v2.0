package com.joss.voodootvdb.provider.movies_people;

import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/9/15
 * Time: 2:08 PM
 */
public class MoviesPeopleProvider {
    public static List<VoodooItem> getVoodooItems(int typeCast, String sectionTitle, MoviesPeopleCursor cursor) {
        List<VoodooItem> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            People people = GGson.fromJson(cursor.getJson(), People.class);
            if(people != null && people.getCast().size() > 0){
                for(Cast cast : people.getCast()){
                    cast.setType(typeCast);
                    cast.setSectionTitle(sectionTitle);
                    items.add(cast);
                }
            }
        }
        return items;
    }
}
