package com.joss.voodootvdb.interfaces;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Show.Show;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 4:08 PM
 */
public interface ListListener {

    public void onClick(Movie movie);

    public void onClick(Show show);

    public void onClick(Person person);

    public void onClick(Episode episode);

}
