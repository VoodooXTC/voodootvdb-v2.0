package com.joss.voodootvdb.provider.person;

import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.utils.GGson;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 6:48 PM
 */
public class PersonProvider {

    public static Person get(PersonCursor personCursor) {
        if(personCursor.moveToFirst()){
            return GGson.fromJson(personCursor.getJson(), Person.class);
        }
        return null;
    }
}
