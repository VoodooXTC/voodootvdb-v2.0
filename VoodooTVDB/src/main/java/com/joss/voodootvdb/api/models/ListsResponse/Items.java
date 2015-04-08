package com.joss.voodootvdb.api.models.ListsResponse;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.api.models.Show.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 4/3/15
 * Time: 2:08 PM
 */
public class Items {

    List<Movie> movies = new ArrayList<>();
    List<Show> shows = new ArrayList<>();
    List<Season> seasons = new ArrayList<>();
    List<Episode> episodes = new ArrayList<>();
    List<Person> persons = new ArrayList<>();

    public Items add(Movie movie){
        this.movies.add(new Movie().setTraktId(movie.getIds().getTrakt()));
        return this;
    }

    public Items add(Show show){
        this.shows.add(new Show().setTraktId(show.getIds().getTrakt()));
        return this;
    }

    public Items add(Season season){
        this.seasons.add(new Season().setTraktId(season.getIds().getTrakt()));
        return this;
    }

    public Items add(Episode episode){
        this.episodes.add(new Episode().setTraktId(episode.getIds().getTrakt()));
        return this;
    }

    public Items add(Person person){
        this.persons.add(new Person().setTraktId(person.getIds().getTrakt()));
        return this;
    }

    public int size(){
        return movies.size() + shows.size() + seasons.size() + episodes.size() + persons.size();
    }

}
