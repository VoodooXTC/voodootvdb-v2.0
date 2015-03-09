package com.joss.voodootvdb.provider.person_shows;

import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 6:54 PM
 */
public class PersonShowsProvider {
    public static People get(PersonShowsCursor personShowsCursor) {
        if(personShowsCursor.moveToFirst()){
            return GGson.fromJson(personShowsCursor.getJson(), People.class);
        }
        return null;
    }

    public static List<VoodooItem> get(int type, String sectionTitle, People people) {
        List<VoodooItem> items = new ArrayList<>();
        for(Cast cast : people.getCast()){
            cast.setType(type);
            cast.setSectionTitle(sectionTitle);
            items.add(cast);
        }
        return items;
    }
}
