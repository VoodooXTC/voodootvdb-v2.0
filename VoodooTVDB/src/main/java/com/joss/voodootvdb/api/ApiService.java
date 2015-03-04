package com.joss.voodootvdb.api;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joss.voodootvdb.BuildConfig;
import com.joss.voodootvdb.DataStore;
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
    public static final int REQUEST_POPULAR_SHOWS = 2;

    public static final String ARGS_USER = "user";
    public static final String ARG_EXTENDED = "extended";

    public static final String RESULT_STATUS = "api_result_status";
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
                    UserModel user = service.login(GGson.fromJson(intent.getStringExtra(ARGS_USER), UserModel.class));
                    DataStore.persistUser(this, user);
                    broadcastLoginSuccess(type);
                    break;

                case REQUEST_POPULAR_SHOWS:
                    Db.updatePopularShows(this, service.getPopularShows(intent.getStringExtra(EXTENDED)));
                    break;
            }
        } catch(RetrofitError e){
            handleRetrofitError(type, e);
        } catch (Exception e) {
            broadcastRequestFailed(type, ERROR_RESPONSE);
        }
    }

    private void broadcastLoginSuccess(int type) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST);
        intent.putExtra(REQUEST_TYPE, type);
        broadcastManager.sendBroadcast(intent);
    }

    /**
     *  Broadcasts
     */
    private void handleRetrofitError(int requestType, RetrofitError error){
        if (error.getKind() == RetrofitError.Kind.NETWORK){
            broadcastRequestFailed(requestType, ERROR_NETWORK);
        } else {
            broadcastRequestFailed(requestType, ERROR_RESPONSE);
        }
    }
    private void broadcastRequestFailed(int requestType, int errorType){
        Intent intent = new Intent(BROADCAST);
        intent.putExtra(REQUEST_TYPE, requestType);
        intent.putExtra(ERROR_TYPE, errorType);
        intent.putExtra(RESULT_STATUS, RESULT_ERROR);
        broadcastManager.sendBroadcast(intent);
    }
}
