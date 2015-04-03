package com.joss.voodootvdb.api.models.ListsResponse;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.api.models.Show.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 4/3/15
 * Time: 1:59 PM
 */
public class NotFound {

    List<Movie> movies = new ArrayList<>();
    List<Show> shows = new ArrayList<>();
    List<Season> seasons = new ArrayList<>();
    List<Episode> episodes = new ArrayList<>();

}
