package com.joss.voodootvdb.provider.shows;

import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.utils.GGson;

/**
 * Created by Jossay
 * Date: 3/7/2015
 * Time: 4:03 PM
 */
public class ShowsProvider {

    public static Show get(ShowsCursor cursor) {
        if(cursor.moveToFirst()){
            return GGson.fromJson(cursor.getJson(), Show.class);
        }
        return null;
    }
}
