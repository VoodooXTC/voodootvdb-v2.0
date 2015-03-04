package com.joss.voodootvdb.api;

import android.content.Context;

import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.api.models.Login.UserModel;

import retrofit.RequestInterceptor;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 5:03 PM
 */
public class VoodooRequestInterceptor implements RequestInterceptor {

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_TRAKT_API_VERSION = "trakt-api-version";
    private static final String HEADER_TRAKT_API_KEY = "trakt-api-key";
    private static final String HEADER_TRAKT_USER_LOGIN = "trakt-user-login";
    public static final String HEADER_TRAKT_USER_TOKEN = "trakt-user-token";

    private static final String APPLICATION_JSON = "application/json";
    private static final String VERSION = "2";
    private static final String TRAKT_API_KEY = "f570f496866f6d1be96a36c00f4439424a9245de";

    Context context;

    public VoodooRequestInterceptor(Context context){
        this.context = context;
    }

    @Override
    public void intercept(RequestFacade request) {

        request.addHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON);
        request.addHeader(HEADER_TRAKT_API_VERSION, VERSION);
        request.addHeader(HEADER_TRAKT_API_KEY, TRAKT_API_KEY);

        UserModel user = DataStore.getUser(context);
        if(user != null){
            request.addHeader(HEADER_TRAKT_USER_LOGIN, user.login);
            request.addHeader(HEADER_TRAKT_USER_TOKEN, user.token);
        }
    }
}
