package com.joss.voodootvdb.api;

import com.joss.voodootvdb.api.models.Login.UserModel;
import com.joss.voodootvdb.api.models.Show.Show;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 10:52 AM
 */
public interface RestService {

    public static final String EXTENDED = "extended";

    @POST("/auth/login")
    UserModel login(@Body UserModel userModel);

    @GET("/shows/popular")
    List<Show> getPopularShows(@Query(EXTENDED) String extended);

}
