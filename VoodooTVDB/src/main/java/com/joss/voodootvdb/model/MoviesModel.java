package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.provider.movies.MoviesCursor;
import com.google.gson.annotations.SerializedName;
import com.joss.voodootvdb.utils.GGson;

/**
 * Model object for the {@code movies}.
 */
public class MoviesModel{
    public String title;
    public Integer year;
    public Integer traktId;
    public String imdbId;
    public String released;
    public Double rating;
    public String updatedAt;
    public String language;
    public String json;

    public MoviesModel(){}

    public MoviesModel(MoviesCursor cursor){
        this.title = cursor.getTitle();
        this.year = cursor.getYear();
        this.traktId = cursor.getTraktId();
        this.imdbId = cursor.getImdbId();
        this.released = cursor.getReleased();
        this.rating = cursor.getRating();
        this.updatedAt = cursor.getUpdatedAt();
        this.language = cursor.getLanguage();
        this.json = cursor.getJson();
    }

    public MoviesModel(Movie movie) {
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.traktId = movie.getIds().getTrakt();
        this.imdbId = movie.getIds().getImdb();
        this.released = movie.getReleased();
        this.rating = movie.getRating();
        this.updatedAt = movie.getUpdatedAt();
        this.language = movie.getLanguage();
        this.json = GGson.toJson(movie);
    }
}