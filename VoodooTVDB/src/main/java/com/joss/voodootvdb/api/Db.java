package com.joss.voodootvdb.api;

import android.content.ContentValues;
import android.content.Context;

import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.model.MoviesModel;
import com.joss.voodootvdb.model.MoviesPeopleModel;
import com.joss.voodootvdb.model.PersonModel;
import com.joss.voodootvdb.model.PersonMoviesModel;
import com.joss.voodootvdb.model.PersonShowsModel;
import com.joss.voodootvdb.model.ShowsModel;
import com.joss.voodootvdb.model.ShowsPeopleModel;
import com.joss.voodootvdb.provider.movies.MoviesColumns;
import com.joss.voodootvdb.provider.movies.MoviesContentValues;
import com.joss.voodootvdb.provider.movies.MoviesProvider;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleColumns;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleContentValues;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedColumns;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedContentValues;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedProvider;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedSelection;
import com.joss.voodootvdb.provider.person.PersonColumns;
import com.joss.voodootvdb.provider.person.PersonContentValues;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesColumns;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesContentValues;
import com.joss.voodootvdb.provider.person_shows.PersonShowsColumns;
import com.joss.voodootvdb.provider.person_shows.PersonShowsContentValues;
import com.joss.voodootvdb.provider.shows.ShowsColumns;
import com.joss.voodootvdb.provider.shows.ShowsContentValues;
import com.joss.voodootvdb.provider.shows.ShowsProvider;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleColumns;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleContentValues;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularContentValues;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularProvider;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedColumns;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedContentValues;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedProvider;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedSelection;

import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 6:04 PM
 */
public class Db {

    public static void updateShow(Context context, Show show) {
        ContentValues showCV = ShowsContentValues.getSingleContentValue(new ShowsModel(show));
        context.getContentResolver().insert(ShowsColumns.CONTENT_URI, showCV);
    }

    public static void updatePopularShows(Context context, List<Show> shows){
        // Update Show Content
        ContentValues[] showsCV = ShowsContentValues.getContentValues(ShowsProvider.get(shows));
        context.getContentResolver().bulkInsert(ShowsColumns.CONTENT_URI, showsCV);

        // Update Popular Shows
        context.getContentResolver().delete(ShowsPopularColumns.CONTENT_URI, null, null);
        ContentValues[] showsPopularCV = ShowsPopularContentValues.getContentValues(ShowsPopularProvider.get(shows));
        context.getContentResolver().bulkInsert(ShowsPopularColumns.CONTENT_URI, showsPopularCV);
    }

    public static void updateShowsRelated(Context context, int traktId, List<Show> shows) {
        // Update Show Content
        ContentValues[] showsCV = ShowsContentValues.getContentValues(ShowsProvider.get(shows));
        context.getContentResolver().bulkInsert(ShowsColumns.CONTENT_URI, showsCV);

        // Update Related Shows
        ShowsRelatedSelection where = new ShowsRelatedSelection();
        where.showTraktId(traktId);
        context.getContentResolver().delete(ShowsRelatedColumns.CONTENT_URI, where.sel(), where.args());

        ContentValues[] showsRelatedCV = ShowsRelatedContentValues.getContentValues(ShowsRelatedProvider.get(traktId, shows));
        context.getContentResolver().bulkInsert(ShowsRelatedColumns.CONTENT_URI, showsRelatedCV);
    }

    public static void updateShowsPeople(Context context, int traktId, People showsPeople) {
        ContentValues showsPeopleCV = ShowsPeopleContentValues.getSingleContentValue(new ShowsPeopleModel(traktId, showsPeople));
        context.getContentResolver().insert(ShowsPeopleColumns.CONTENT_URI, showsPeopleCV);
    }

    public static void updatePerson(Context context, Person person) {
        ContentValues personCV = PersonContentValues.getSingleContentValue(new PersonModel(person));
        context.getContentResolver().insert(PersonColumns.CONTENT_URI, personCV);
    }

    public static void updatePersonShows(Context context, int traktId, People personShows) {
        ContentValues personShowsCV = PersonShowsContentValues.getSingleContentValue(new PersonShowsModel(traktId, personShows));
        context.getContentResolver().insert(PersonShowsColumns.CONTENT_URI, personShowsCV);
    }

    public static void updatePersonMovies(Context context, int traktId, People personMovies){
        ContentValues personMoviesCV = PersonMoviesContentValues.getSingleContentValue(new PersonMoviesModel(traktId, personMovies));
        context.getContentResolver().insert(PersonMoviesColumns.CONTENT_URI, personMoviesCV);
    }

    public static void updateMovie(Context context, Movie movie) {
        ContentValues movieCV = MoviesContentValues.getSingleContentValue(new MoviesModel(movie));
        context.getContentResolver().insert(MoviesColumns.CONTENT_URI, movieCV);
    }

    public static void updateMoviesPeople(Context context, int traktId, People moviesPeople) {
        ContentValues moviesPeopleCV = MoviesPeopleContentValues.getSingleContentValue(new MoviesPeopleModel(traktId, moviesPeople));
        context.getContentResolver().insert(MoviesPeopleColumns.CONTENT_URI, moviesPeopleCV);
    }

    public static void updateMoviesRelated(Context context, int traktId, List<Movie> moviesRelated) {
        // Update Movies Content
        ContentValues[] moviesCV = MoviesContentValues.getContentValues(MoviesProvider.get(moviesRelated));
        context.getContentResolver().bulkInsert(MoviesColumns.CONTENT_URI, moviesCV);

        // Update Movies Related
        MoviesRelatedSelection where = new MoviesRelatedSelection();
        where.movieTraktId(traktId);
        context.getContentResolver().delete(MoviesRelatedColumns.CONTENT_URI, where.sel(), where.args());

        ContentValues[] moviesRelatedCV = MoviesRelatedContentValues.getContentValues(MoviesRelatedProvider.get(traktId, moviesRelated));
        context.getContentResolver().bulkInsert(MoviesRelatedColumns.CONTENT_URI, moviesRelatedCV);
    }
}
