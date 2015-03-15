package com.joss.voodootvdb.api;

import android.content.Context;
import android.content.Intent;

import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;
import com.joss.voodootvdb.utils.GGson;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 10:52 AM
 */
public class Api {

    private static String buildExtendedValues(String[] extended) {
        String extendedValues = "";
        for(String ex : extended){
            extendedValues += ex + ",";
        }
        return extendedValues;
    }

    public static void login(Context context, AccessTokenRequest accessTokenRequest){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_LOGIN_ACCESS_TOKEN);
        intent.putExtra(ApiService.ARG_ACCESS_TOKEN, GGson.toJson(accessTokenRequest));
        context.startService(intent);
    }

    public static void getPopularShows(Context context){
        getPopularShows(context, ApiService.EXT_IMAGES, ApiService.EXT_FULL);
    }

    public static void getPopularShows(Context context, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_SHOWS_POPULAR);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getShow(Context context, int traktId){
        getShow(context, traktId,
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES,
                ApiService.EXT_METADATA);
    }

    public static void getShow(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_SHOW);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getShowRelated(Context context, int traktId){
        getShowRelated(context, traktId,
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES,
                ApiService.EXT_METADATA);
    }

    public static void getShowRelated(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_SHOWS_RELATED);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getShowPeople(Context context, int traktId){
        getShowPeople(context, traktId,
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES,
                ApiService.EXT_METADATA);
    }

    public static void getShowPeople(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_SHOWS_PEOPLE);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getPerson(Context context, int traktId){
        getPerson(context, traktId, ApiService.EXT_IMAGES, ApiService.EXT_FULL);
    }

    public static void getPerson(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_PERSON);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getPersonMovies(Context context, int traktId){
        getPersonMovies(context, traktId, ApiService.EXT_IMAGES, ApiService.EXT_FULL);
    }

    public static void getPersonMovies(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_PERSON_MOVIES);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getPersonShows(Context context, int traktId){
        getPersonShows(context, traktId, ApiService.EXT_IMAGES, ApiService.EXT_FULL);
    }

    public static void getPersonShows(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_PERSON_SHOWS);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getMovie(Context context, int traktId){
        getMovie(context, traktId,
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES,
                ApiService.EXT_METADATA);
    }

    public static void getMovie(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_MOVIE);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getMoviesRelated(Context context, int traktId){
        getMoviesRelated(context, traktId,
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES,
                ApiService.EXT_METADATA);
    }

    public static void getMoviesRelated(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_MOVIES_RELATED);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getMoviesPeople(Context context, int traktId){
        getMoviesPeople(context, traktId,
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES,
                ApiService.EXT_METADATA);
    }

    public static void getMoviesPeople(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_MOVIES_PEOPLE);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getSeasons(Context context, int traktId){
        getSeasons(context, traktId,
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES);
    }

    public static void getSeasons(Context context, int traktId, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_SEASONS);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getEpisodes(Context context, int traktId, int seasonNumber){
        getEpisodes(context, traktId, seasonNumber, ApiService.EXT_FULL, ApiService.EXT_IMAGES);
    }

    public static void getEpisodes(Context context, int traktId, int seasonNumber, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_EPISODES);
        intent.putExtra(ApiService.ARG_ID, traktId);
        intent.putExtra(ApiService.ARG_SEASON_NUMBER, seasonNumber);
        intent.putExtra(ApiService.ARG_EXTENDED, buildExtendedValues(extended));
        context.startService(intent);
    }

    public static void getEpisodesWatched(Context context, int traktId){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_EPISODES_WATCHED);
        intent.putExtra(ApiService.ARG_ID, traktId);
        context.startService(intent);
    }

    public static void search(Context context, String query, String type, int page, int limit){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_SEARCH);
        intent.putExtra(ApiService.ARG_QUERY, query);
        intent.putExtra(ApiService.ARG_TYPE, type);
        intent.putExtra(ApiService.ARG_PAGE, page);
        intent.putExtra(ApiService.ARG_LIMIT, limit);
        context.startService(intent);
    }

    public static void searchAllTypes(Context context, String query){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_SEARCH_ALL_TYPES);
        intent.putExtra(ApiService.ARG_QUERY, query);
        context.startService(intent);
    }
}
