package com.joss.voodootvdb.api.models.CustomLists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Show.Show;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 1:22 PM
 */
public class CustomListItem {

    public static final String TYPE_MOVIE = "movie";
    public static final String TYPE_SHOW = "show";
    public static final String TYPE_EPISODE = "episode";
    public static final String TYPE_PERSON = "person";

    @Expose
    @SerializedName("listed_at")
    private String listedAt;
    @Expose
    private String type;
    @Expose
    private Show show;
    @Expose
    private Episode episode;
    @Expose
    private Movie movie;
    @Expose
    private Person person;

    public String getListedAt() {
        return listedAt == null ? "" : listedAt;
    }

    public void setListedAt(String listedAt) {
        this.listedAt = listedAt;
    }

    public String getType() {
        return type  == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Show getShow() {
        return show == null ? new Show() : show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Episode getEpisode() {
        return episode == null ? new Episode() : episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Movie getMovie() {
        return movie == null ? new Movie() : movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person == null ? new Person() : person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getTraktId() {
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

    public String getTitle() {
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

    public String getSubtitle() {
        switch (type){
            case TYPE_MOVIE:
                return movie.getOverview();
            case TYPE_SHOW:
                return show.getOverview();
            case TYPE_EPISODE:
                return episode.getOverview();
            case TYPE_PERSON:
                return person.getBiography();
            default:
                return "";
        }
    }

    public String getImage() {
        switch (type){
            case TYPE_MOVIE:
                return movie.getImages().getPoster().getFull();
            case TYPE_SHOW:
                return show.getImages().getPoster().getFull();
            case TYPE_EPISODE:
                return episode.getImages().getPoster().getFull();
            case TYPE_PERSON:
                return person.getImages().getHeadshot().getFull();
            default:
                return "";
        }
    }
}
