package com.joss.voodootvdb.api.models.Search;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Show.Show;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 12:07 AM
 */
public class Search {

    public static final String TYPE_MOVIE = "movie";
    public static final String TYPE_SHOW = "show";
    public static final String TYPE_EPISODE = "episode";
    public static final String TYPE_PERSON = "person";
    public static final String TYPE_LIST = "list";

    public String type;
    public Double score;
    public Movie movie;
    public Show show;
    public Episode episode;
    public Person person;

    public int getId(){
        if(type == null)
            return  0;

        switch (type){
            case TYPE_MOVIE:
                return movie.getIds().getTrakt();
            case TYPE_SHOW:
                return show.getIds().getTrakt();
            case TYPE_EPISODE:
                return episode.getIds().getTrakt();
            case TYPE_PERSON:
                return person.getIds().getTrakt();
            default:
                return 0;
        }
    }

    public String getTitle(){
        if(type == null)
            return  "";

        switch (type){
            case TYPE_MOVIE:
                return movie.getTitle();
            case TYPE_SHOW:
                return show.getTitle();
            case TYPE_EPISODE:
                return episode.getTitle();
            case TYPE_PERSON:
                return person.getName();
            default:
                return "";
        }
    }

    public int getIcon(){
        if(type == null)
            return  0;

        switch (type){
            case TYPE_MOVIE:
                return R.drawable.ic_movie;
            case TYPE_SHOW:
                return R.drawable.ic_tv;
            case TYPE_EPISODE:
                return R.drawable.ic_tv;
            case TYPE_PERSON:
                return R.drawable.ic_user;
            default:
                return 0;
        }
    }
}
