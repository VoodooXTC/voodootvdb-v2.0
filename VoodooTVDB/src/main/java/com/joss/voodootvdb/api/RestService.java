package com.joss.voodootvdb.api;

import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.Login.AccessToken;
import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.api.models.Show.Show;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 10:52 AM
 */
public interface RestService {

    public static final String EXTENDED = "extended";

    @POST("/oauth/token")
    AccessToken login(@Body AccessTokenRequest token);

    @GET("/shows/{id}")
    Show getShow(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/shows/{id}/related")
    List<Show> getShowsRelated(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/shows/{id}/people")
    People getShowsPeople(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/shows/popular")
    List<Show> getShowsPopular(@Query(EXTENDED) String extended);

    @GET("/people/{id}")
    Person getPerson(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/people/{id}/shows")
    People getPersonShows(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/people/{id}/movies")
    People getPersonMovies(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/movies/{id}")
    Movie getMovie(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/movies/{id}/related")
    List<Movie> getMoviesRelated(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/movies/{id}/people")
    People getMoviesPeople(@Path("id") int id, @Query(EXTENDED) String extended);

    @GET("/shows/{id}/seasons")
    List<Season> getSeasons(@Path("id") int showTraktId, @Query(EXTENDED) String extended);

    @GET("/shows/{id}/seasons/{number}")
    List<Episode> getEpisodes(@Path("id") int showTraktId, @Path("number") int seasonNumber);
}
