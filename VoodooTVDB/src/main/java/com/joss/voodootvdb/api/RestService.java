package com.joss.voodootvdb.api;

import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.api.models.Login.AccessToken;
import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Progress.Watched;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.api.models.Settings.Settings;
import com.joss.voodootvdb.api.models.Show.Show;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 10:52 AM
 */
public interface RestService {

    public static final String AUTHORIZATION = "Authorization";
    public static final String EXTENDED = "extended";

    @POST("/oauth/token")
    AccessToken login(@Body AccessTokenRequest token);

    @GET("/users/settings")
    Settings getUserSettings(@Header(AUTHORIZATION) String authorization);

    @GET("/users/{username}/lists")
    List<CustomList> getLists(@Path("username") String usernamem, @Header(AUTHORIZATION) String authorization);

    @GET("/users/{username}/lists/{listTraktId}/items")
    List<CustomListItem> getListItems(@Path("username") String username, @Path("listTraktId") int listTraktId, @Header(AUTHORIZATION) String authorization, @Query(EXTENDED) String extended);

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
    List<Episode> getEpisodes(@Path("id") int showTraktId, @Path("number") int seasonNumber, @Query(EXTENDED) String extended);

    @GET("/shows/{id}/progress/watched")
    Watched getWatchedEpisodes(@Header(AUTHORIZATION) String authorization, @Path("id") int showTraktId);

    @GET("/search")
    List<Search> search(@Query("query") String query, @Query("type") String type, @Query("page") int page, @Query("limit") int limit);
}
