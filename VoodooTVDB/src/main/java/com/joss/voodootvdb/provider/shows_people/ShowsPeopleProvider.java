package com.joss.voodootvdb.provider.shows_people;

import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 2:15 PM
 */
public class ShowsPeopleProvider {
    public static People get(ShowsPeopleCursor cursorPeople) {
        if(cursorPeople.moveToFirst()){
            return GGson.fromJson(cursorPeople.getJson(), People.class);
        }
        return null;
    }

    public static List<VoodooItem> getVoodooItems(int type, String sectionTitle, ShowsPeopleCursor cursorPeople) {
        List<VoodooItem> items = new ArrayList<>();
        if(cursorPeople.moveToFirst()){
            People people = GGson.fromJson(cursorPeople.getJson(), People.class);
            if(people != null && people.getCast().size() > 0){
                for(Cast cast : people.getCast()){
                    cast.setType(type);
                    cast.setSectionTitle(sectionTitle);
                    items.add(cast);
                }
            }
        }
        return items;
    }
}
