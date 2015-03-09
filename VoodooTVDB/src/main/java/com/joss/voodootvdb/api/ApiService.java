package com.joss.voodootvdb.api;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joss.voodootvdb.BuildConfig;
import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Error;
import com.joss.voodootvdb.api.models.Login.UserModel;
import com.joss.voodootvdb.utils.GGson;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 10:52 AM
 */
public class ApiService extends IntentService {

    private static RestService service;
    private LocalBroadcastManager broadcastManager;

    public static final String BROADCAST = "action_api_request";
    public static final String SERVICE = "voodoo_api_service";

    public static final String REQUEST_TYPE = "api_request_type";
    public static final int REQUEST_LOGIN = 1;
    public static final int REQUEST_SHOW = 2;
    public static final int REQUEST_SHOWS_POPULAR = 3;
    public static final int REQUEST_SHOWS_RELATED = 4;
    public static final int REQUEST_SHOWS_PEOPLE = 5;
    public static final int REQUEST_PERSON = 6;
    public static final int REQUEST_PERSON_SHOWS = 7;
    public static final int REQUEST_PERSON_MOVIES = 8;

    public static final String ARG_ID = "id";
    public static final String ARGS_USER = "user";
    public static final String ARG_EXTENDED = "extended";

    public static final String RESULT_MESSAGE = "message";
    public static final String RESULT_STATUS = "result_status";
    public static final int RESULT_ERROR = 0;
    public static final int RESULT_SUCCESS = 1;

    public static final String ERROR_TYPE = "api_error_type";
    public static final int ERROR_UNSPECIFIED = -1;
    public static final int ERROR_NETWORK = 1;
    public static final int ERROR_RESPONSE = 2;

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

        service = getApi(this);
        broadcastManager = LocalBroadcastManager.getInstance(this);
    }

    public static RestService getApi(Context context) {
        if (service == null) {

            Gson gson = new GsonBuilder()
                    .create();

            VoodooRequestInterceptor requestInterceptor = new VoodooRequestInterceptor(context);
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Endpoints.URL)
                    .setRequestInterceptor(requestInterceptor)
                    .setClient(new VoodooAuthClient(context))
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(BuildConfig.DEBUG
                            ? RestAdapter.LogLevel.FULL
                            : RestAdapter.LogLevel.NONE)
                    .build();
            service = restAdapter.create(RestService.class);
        }
        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int type = intent.getIntExtra(REQUEST_TYPE, -1);

        try{
            switch (type){
                case REQUEST_LOGIN:
                    UserModel loginUser = GGson.fromJson(intent.getStringExtra(ARGS_USER), UserModel.class);
                    UserModel user = service.login(loginUser);

                    loginUser.token = user.token;
                    DataStore.persistUser(this, loginUser);

                    broadcastLoginSuccess(type);
                    break;

                case REQUEST_SHOW:
                    Db.updateShow(this, service.getShow(intent.getIntExtra(ARG_ID, 0), intent.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_SHOWS_POPULAR:
                    Db.updatePopularShows(this, service.getShowsPopular(intent.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_SHOWS_RELATED:
                    Db.updateShowsRelated(this,
                            intent.getIntExtra(ARG_ID, 0),
                            service.getShowsRelated(intent.getIntExtra(ARG_ID, 0), intent.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_SHOWS_PEOPLE:
                    Db.updateShowsPeople(this, intent.getIntExtra(ARG_ID, 0), service.getShowsPeople(intent.getIntExtra(ARG_ID, 0), intent.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_PERSON:
                    Db.updatePerson(this, service.getPerson(intent.getIntExtra(ARG_ID, 0), intent.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_PERSON_SHOWS:
                    Db.updatePersonShows(this, intent.getIntExtra(ARG_ID, 0), service.getPersonShows(intent.getIntExtra(ARG_ID, 0), intent.getStringExtra(EXTENDED)));
                    break;

                case REQUEST_PERSON_MOVIES:
                    Db.updatePersonMovies(this, intent.getIntExtra(ARG_ID, 0), service.getPersonMovies(intent.getIntExtra(ARG_ID, 0), intent.getStringExtra(EXTENDED)));
                    break;
            }
        } catch(RetrofitError e){
            handleRetrofitError(type, e);
        } catch (Exception e) {
            broadcastRequestFailed(type, ERROR_RESPONSE, getString(R.string.error_generic_network));
        }
    }

    private void broadcastLoginSuccess(int type) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST);
        intent.putExtra(REQUEST_TYPE, type);
        intent.putExtra(RESULT_STATUS, RESULT_SUCCESS);
        broadcastManager.sendBroadcast(intent);
    }

    /**
     *  Broadcasts
     */
    private void handleRetrofitError(int requestType, RetrofitError error){
        if (error.getKind() == RetrofitError.Kind.NETWORK){
            broadcastRequestFailed(requestType, ERROR_NETWORK, getString(R.string.error_generic_network));
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
        intent.putExtra(ERROR_TYPE, errorType);
        intent.putExtra(RESULT_STATUS, RESULT_ERROR);
        intent.putExtra(RESULT_MESSAGE, message);
        broadcastManager.sendBroadcast(intent);
    }
}
