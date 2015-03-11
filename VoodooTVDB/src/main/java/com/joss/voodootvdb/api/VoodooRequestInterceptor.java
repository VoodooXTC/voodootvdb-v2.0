package com.joss.voodootvdb.api;

import android.content.Context;

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
    public static final String HEADER_AUTHORIZATION = "Authorization";

    private static final String APPLICATION_JSON = "application/json";
    private static final String VERSION = "2";
    public static final String TRAKT_API_KEY = "eb78e260fea060a63edc2857fa41ddee8002674c899ae6705dfbedeada8f4a46";

    Context context;

    public VoodooRequestInterceptor(Context context){
        this.context = context;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON);
        request.addHeader(HEADER_TRAKT_API_VERSION, VERSION);
        request.addHeader(HEADER_TRAKT_API_KEY, TRAKT_API_KEY);
    }
}
