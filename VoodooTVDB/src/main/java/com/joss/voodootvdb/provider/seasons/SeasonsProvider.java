package com.joss.voodootvdb.provider.seasons;

import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.model.SeasonsModel;
import com.joss.voodootvdb.utils.GGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/12/15
 * Time: 4:20 PM
 */
public class SeasonsProvider {
    public static List<SeasonsModel> get(int showTrakId, List<Season> seasons) {
        List<SeasonsModel> items = new ArrayList<>();
        for(Season s : seasons){
            items.add(new SeasonsModel(showTrakId, s));
        }
        return items;
    }

    public static List<Season> get(SeasonsCursor seasonsCursor) {
        List<Season> items = new ArrayList<>();
        if(seasonsCursor.moveToFirst()){
            while (!seasonsCursor.isAfterLast()){
                items.add(GGson.fromJson(seasonsCursor.getJson(), Season.class));
                seasonsCursor.moveToNext();
            }
        }
        return items;
    }
}
