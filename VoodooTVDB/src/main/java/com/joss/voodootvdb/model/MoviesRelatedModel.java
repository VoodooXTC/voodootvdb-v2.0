package com.joss.voodootvdb.model;

import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedCursor;
import com.google.gson.annotations.SerializedName;

/**
 * Model object for the {@code movies_related}.
 */
public class MoviesRelatedModel{
    public Integer movieTraktId;
    public Integer relatedTraktId;

    public MoviesRelatedModel(){}

    public MoviesRelatedModel(MoviesRelatedCursor cursor){
        this.movieTraktId = cursor.getMovieTraktId();
        this.relatedTraktId = cursor.getRelatedTraktId();
    }

    public MoviesRelatedModel(int traktId, Movie m) {
        this.movieTraktId = traktId;
        this.relatedTraktId = m.getIds().getTrakt();
    }
}