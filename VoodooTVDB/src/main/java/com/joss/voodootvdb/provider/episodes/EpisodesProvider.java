package com.joss.voodootvdb.provider.episodes;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.model.EpisodesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/12/15
 * Time: 4:27 PM
 */
public class EpisodesProvider {
    public static List<EpisodesModel> get(int showTraktId, List<Episode> episodes) {
        List<EpisodesModel> items = new ArrayList<>();
        for(Episode e : episodes){
            items.add(new EpisodesModel(showTraktId, e));
        }
        return items;
    }
}
