package com.joss.voodootvdb.api;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joss.voodootvdb.BuildConfig;
import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.api.models.Error;
import com.joss.voodootvdb.api.models.ListsResponse.Items;
import com.joss.voodootvdb.api.models.ListsResponse.ListResponse;
import com.joss.voodootvdb.api.models.Login.AccessToken;
import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.api.models.Settings.Settings;
import com.joss.voodootvdb.provider.lists.ListsProvider;
import com.joss.voodootvdb.utils.GGson;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.GsonConverter;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 10:52 AM
 */
public class ApiService extends IntentService {

    public static final String TAG = ApiService.class.getSimpleName();
    private static final int DEFAULT_TIMEOUT = 5000; // Default 5 second timeout per call

    private static RestService service;
    private LocalBroadcastManager broadcastManager;

    public static final String BROADCAST = "action_api_request";
    public static final String SERVICE = "voodoo_api_service";

    public static final String REQUEST_TYPE = "api_request_type";
    public static final int REQUEST_LOGIN_ACCESS_TOKEN = 1;
    public static final int REQUEST_SHOW = 2;
    public static final int REQUEST_SHOWS_POPULAR = 3;
    public static final int REQUEST_SHOWS_RELATED = 4;
    public static final int REQUEST_SHOWS_PEOPLE = 5;
    public static final int REQUEST_PERSON = 6;
    public static final int REQUEST_PERSON_SHOWS = 7;
    public static final int REQUEST_PERSON_MOVIES = 8;
    public static final int REQUEST_MOVIE = 9;
    public static final int REQUEST_MOVIES_RELATED = 10;
    public static final int REQUEST_MOVIES_PEOPLE = 11;
    public static final int REQUEST_SEASONS = 12;
    public static final int REQUEST_EPISODES = 13;
    public static final int REQUEST_EPISODES_WATCHED = 14;
    public static final int REQUEST_SEARCH = 15;
    public static final int REQUEST_SEARCH_ALL_TYPES = 16;
    public static final int REQUEST_USER_SETTINGS = 17;
    public static final int REQUEST_USER_LISTS = 18;
    public static final int REQUEST_USER_LIST_ITEMS = 19;
    public static final int REQUEST_USER_LIST_ITEMS_ADD = 20;
    public static final int REQUEST_USER_LIST_ITEMS_REMOVE = 21;
    public static final int REQUEST_WATCHLIST = 22;
    public static final int REQUEST_WATCHLIST_ADD = 23;
    public static final int REQUEST_WATCHLIST_REMOVE = 24;

    public static final String ARG_ID = "id";
    public static final String ARG_EXTENDED = "extended";
    public static final String ARG_ACCESS_TOKEN = "access_token";
    public static final String ARG_SEASON_NUMBER = "season_number";
    public static final String ARG_QUERY = "query";
    public static final String ARG_TYPE = "type";
    public static final String ARG_PAGE = "page";
    public static final String ARG_LIMIT = "limit";
    public static final String ARG_SEARCH_RESULTS = "search_results";
    public static final String ARG_ITEMS = "items";
    public static final String ARG_STATUS = "status";

    public static final String RESULT_MESSAGE = "message";
    public static final String RESULT_STATUS = "result_status";
    public static final int RESULT_ERROR = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_NETWORK_STATUS = 2;

    public static final String ERROR_TYPE = "api_error_type";
    public static final int ERROR_UNSPECIFIED = -1;
    public static final int ERROR_NETWORK = 1;
    public static final int ERROR_RESPONSE = 2;
    public static final int ERROR_EMPTY = 3;

    public static final String EXTENDED = "extended";
    public static final String EXT_MIN = "min";
    public static final String EXT_IMAGES = "images";
    public static final String EXT_FULL = "full";
    public static final String EXT_METADATA = "metadata";

    public ApiService() {
        super(SERVICE);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        service = getApi(this, null);
        broadcastManager = LocalBroadcastManager.getInstance(this);
    }

    public static RestService getApi(Context context, UrlConnectionClient client) {
        if (service == null) {

            Gson gson = new GsonBuilder()
                    .create();

            OkHttpClient client1 = new OkHttpClient();
            client1.networkInterceptors().add(new StethoInterceptor());

            VoodooRequestInterceptor requestInterceptor = new VoodooRequestInterceptor(context);
            service = new RestAdapter.Builder()
                    .setEndpoint(Endpoints.URL)
                    .setRequestInterceptor(requestInterceptor)
                    .setClient(new VoodooAuthClient(context))
                    .setConverter(new GsonConverter(gson))
                    .setClient(new OkClient(client1))
//                    .setClient(client == null ? new TimeOutClient(DEFAULT_TIMEOUT) : client)
                    .setLogLevel(BuildConfig.DEBUG
                            ? RestAdapter.LogLevel.FULL
                            : RestAdapter.LogLevel.NONE)
                    .build()
                    .create(RestService.class);
        }
        return service;
    }

    @Override
    protected void onHandleIntent(Intent i) {
        int type = i.getIntExtra(REQUEST_TYPE, -1);

        try{
            switch (type){
                case REQUEST_LOGIN_ACCESS_TOKEN:
                    AccessTokenRequest accessTokenRequest = GGson.fromJson(i.getStringExtra(ARG_ACCESS_TOKEN), AccessTokenRequest.class);
                    AccessToken accessToken = service.login(accessTokenRequest);
                    DataStore.persistAccessToken(this, accessToken);
                    broadcastSuccess(type);
                    break;

                case REQUEST_USER_SETTINGS:
                    Settings userSettings = service.getUserSettings(DataStore.getAuthorizationToken(this));
                    if(userSettings != null){
                        DataStore.persistUserSettings(this, userSettings);
                        broadcastSuccess(type);
                    }else{
                        broadcastRequestFailed(type, ERROR_RESPONSE, getString(R.string.error_generic_network));
                    }
                    break;

                case REQUEST_SHOW:
                    Db.updateShow(this, service.getShow(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_SHOWS_POPULAR:
                    Db.updatePopularShows(this, service.getShowsPopular(i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_SHOWS_RELATED:
                    Db.updateShowsRelated(this,
                            i.getIntExtra(ARG_ID, 0),
                            service.getShowsRelated(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_SHOWS_PEOPLE:
                    Db.updateShowsPeople(this, i.getIntExtra(ARG_ID, 0), service.getShowsPeople(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_PERSON:
                    Db.updatePerson(this, service.getPerson(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_PERSON_SHOWS:
                    Db.updatePersonShows(this, i.getIntExtra(ARG_ID, 0), service.getPersonShows(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_PERSON_MOVIES:
                    Db.updatePersonMovies(this, i.getIntExtra(ARG_ID, 0), service.getPersonMovies(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_MOVIE:
                    Db.updateMovie(this, service.getMovie(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_MOVIES_PEOPLE:
                    Db.updateMoviesPeople(this, i.getIntExtra(ARG_ID, 0), service.getMoviesPeople(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_MOVIES_RELATED:
                    Db.updateMoviesRelated(this, i.getIntExtra(ARG_ID, 0), service.getMoviesRelated(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_SEASONS:
                    Db.updateSeasons(this, i.getIntExtra(ARG_ID, 0), service.getSeasons(i.getIntExtra(ARG_ID, 0), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_EPISODES:
                    Db.updateEpisodes(this, i.getIntExtra(ARG_ID, 0), service.getEpisodes(i.getIntExtra(ARG_ID, 0), i.getIntExtra(ARG_SEASON_NUMBER, -1), i.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_EPISODES_WATCHED:
                    Db.updateWatchedEpisodes(this, i.getIntExtra(ARG_ID, 0), service.getWatchedEpisodes(DataStore.getAuthorizationToken(this), i.getIntExtra(ARG_ID, 0)));
                    break;

                case REQUEST_SEARCH:
                    broadcastSearchSuccess(type, service.search(i.getStringExtra(ARG_QUERY), i.getStringExtra(ARG_TYPE), i.getIntExtra(ARG_PAGE, 1), i.getIntExtra(ARG_LIMIT, 10)));
                    break;

                case REQUEST_SEARCH_ALL_TYPES:
                    // Set the timeout
                    service = null;
                    service = getApi(this, new TimeOutClient(333));

                    List<Search> searchResults = new ArrayList<>();
                    String query = i.getStringExtra(ARG_QUERY);
                    searchResults.addAll(service.search(query, Search.TYPE_SHOW, 1, 5));
                    searchResults.addAll(service.search(query, Search.TYPE_MOVIE, 1, 5));
                    searchResults.addAll(service.search(query, Search.TYPE_PERSON, 1, 5));
                    broadcastSearchSuccess(type, searchResults);

                    // Reset the timeout
                    service = null;
                    service = getApi(this, null);
                    break;

                case REQUEST_USER_LISTS:
                    List<CustomList> lists = service.getLists(DataStore.getUsername(this), DataStore.getAuthorizationToken(this));
                    Db.updateLists(this, lists);
                    if(lists == null || lists.size() == 0)
                        broadcastRequestFailed(type, ERROR_EMPTY, "");
                    break;

                case REQUEST_USER_LIST_ITEMS:
                    List<CustomListItem> customListItems = service.getListItems(DataStore.getUsername(this), i.getIntExtra(ARG_ID, 0), DataStore.getAuthorizationToken(this), i.getStringExtra(EXTENDED));
                    Db.updateListItems(this, i.getIntExtra(ARG_ID, 0), customListItems);
                    if(customListItems == null || customListItems.size() == 0)
                        broadcastRequestFailed(type, ERROR_RESPONSE, "");
                    break;

                case REQUEST_USER_LIST_ITEMS_ADD:
                    ListResponse listResponse = service.addItemsToList(DataStore.getUsername(this), i.getStringExtra(ARG_ID), DataStore.getAuthorizationToken(this), GGson.fromJson(i.getStringExtra(ARG_ITEMS), Items.class));
                    if(listResponse.addedSize() > 0) {
                        ListsProvider.markListStale(this, i.getIntExtra(ARG_ID, 0));
                        Api.getListItems(this, i.getIntExtra(ARG_ID, 0));
                    }
                    break;

                case REQUEST_USER_LIST_ITEMS_REMOVE:
                    ListResponse removeListResponse = service.removeItemsFromList(DataStore.getUsername(this), i.getIntExtra(ARG_ID, 0), DataStore.getAuthorizationToken(this), GGson.fromJson(i.getStringExtra(ARG_ITEMS), Items.class));
                    if(removeListResponse.removedSize() > 0) {
                        ListsProvider.markListStale(this, i.getIntExtra(ARG_ID, 0));
                        Api.getListItems(this, i.getIntExtra(ARG_ID, 0));
                    }
                    break;

                case REQUEST_WATCHLIST:
                    List<CustomListItem> watchlistItems = service.getWatchlist(DataStore.getAuthorizationToken(this), i.getStringExtra(EXTENDED));
                    Db.updateListItems(this, CustomList.WATCHLIST_ID, watchlistItems);
                    if(watchlistItems == null || watchlistItems.size() == 0)
                        broadcastRequestFailed(type, ERROR_EMPTY, "");
                    break;

                case REQUEST_WATCHLIST_ADD:
                    ListResponse addWatchListResponse = service.addWatchlist(DataStore.getAuthorizationToken(this), GGson.fromJson(i.getStringExtra(ARG_ITEMS), Items.class));
                    if(addWatchListResponse.addedSize() == 0) {
                        broadcastRequestFailed(type, ERROR_EMPTY, "");
                    }else{
                        Api.getWatchlistItems(this);
                    }
                    break;

                case REQUEST_WATCHLIST_REMOVE:
                    ListResponse removeWatchListResponse = service.removeWatchlist(DataStore.getAuthorizationToken(this), GGson.fromJson(i.getStringExtra(ARG_ITEMS), Items.class));
                    if(removeWatchListResponse.removedSize() == 0) {
                        broadcastRequestFailed(type, ERROR_EMPTY, "");
                    }else{
                        Api.getWatchlistItems(this);
                    }
                    break;
            }
        } catch(RetrofitError e){
            handleRetrofitError(type, e);
        } catch (Exception e) {
            broadcastRequestFailed(type, ERROR_RESPONSE, getString(R.string.error_generic_network));
        }
    }

    private void broadcastNetworkStatus(String status) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST);
        intent.putExtra(RESULT_STATUS, RESULT_NETWORK_STATUS);
        intent.putExtra(ARG_STATUS, status);
        broadcastManager.sendBroadcast(intent);
    }

    private void broadcastSuccess(int type) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST);
        intent.putExtra(REQUEST_TYPE, type);
        intent.putExtra(RESULT_STATUS, RESULT_SUCCESS);
        broadcastManager.sendBroadcast(intent);
    }

    private void broadcastSearchSuccess(int type, List<Search> items){
        Intent intent = new Intent();
        intent.setAction(BROADCAST);
        intent.putExtra(REQUEST_TYPE, type);
        intent.putExtra(RESULT_STATUS, RESULT_SUCCESS);
        intent.putExtra(ARG_SEARCH_RESULTS, GGson.toJson(items));
        broadcastManager.sendBroadcast(intent);
    }

    /**
     *  Broadcasts
     */
    private void handleRetrofitError(int requestType, RetrofitError error){
        if (error.getKind() == RetrofitError.Kind.NETWORK){
            broadcastRequestFailed(requestType, ERROR_NETWORK, getString(R.string.error_generic_network));
        } else if(error.getKind() == RetrofitError.Kind.HTTP && error.getResponse().getStatus() == 503){
            broadcastRequestFailed(requestType, ERROR_RESPONSE, getString(R.string.error_server_overloaded));
        } else {
            try {
                broadcastRequestFailed(requestType, ERROR_RESPONSE, ((Error) error.getBodyAs(Error.class)).message);
            } catch (Exception e){
                broadcastRequestFailed(requestType, ERROR_RESPONSE, getString(R.string.error_generic_network));
            }
        }
    }

    private void broadcastRequestFailed(int requestType, int errorType, String message){
        Intent intent = new Intent(BROADCAST);
        intent.putExtra(REQUEST_TYPE, requestType);
        intent.putExtra(RESULT_STATUS, RESULT_ERROR);
        intent.putExtra(RESULT_MESSAGE, message);
        intent.putExtra(ERROR_TYPE, errorType);
        broadcastManager.sendBroadcast(intent);
    }
}
