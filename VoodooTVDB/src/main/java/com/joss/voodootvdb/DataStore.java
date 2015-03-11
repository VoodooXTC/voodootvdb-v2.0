package com.joss.voodootvdb;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.joss.voodootvdb.api.models.Login.AccessToken;
import com.joss.voodootvdb.api.models.Login.AccessTokenRequest;
import com.joss.voodootvdb.utils.GGson;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:16 PM
 */
public class DataStore {

    private static String FIRST_LAUNCH = "first";
    private static String ACCESS_TOKEN_REQUEST = "access_token_request";
    private static String ACCESS_TOKEN = "access_token";

    private static SharedPreferences getDataStore(Context context) {
        return new EncryptedSharedPreferences(context, PreferenceManager.getDefaultSharedPreferences(context));
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getDataStore(context).edit();
    }

    private static SharedPreferences getPrefs(Context context) {
        return getDataStore(context);
    }


    public static boolean getFirstLaunch(Context context){
        return getPrefs(context).getBoolean(FIRST_LAUNCH, true);
    }

    public static void persistFirstLaunch(Context context, boolean broadcast){
        getEditor(context).putBoolean(FIRST_LAUNCH, broadcast).commit();
    }

    public static AccessTokenRequest getAccessTokenRequest(Context context){
        return GGson.fromJson(getPrefs(context).getString(ACCESS_TOKEN_REQUEST, null), AccessTokenRequest.class);
    }

    public static void persistAccessTokenRequest(Context context, AccessTokenRequest accessTokenRequest){
        getEditor(context).putString(ACCESS_TOKEN_REQUEST, GGson.toJson(accessTokenRequest)).commit();
    }

    public static AccessToken getAccessToken(Context context){
        return GGson.fromJson(getPrefs(context).getString(ACCESS_TOKEN, null), AccessToken.class);
    }

    public static void persistAccessToken(Context context, AccessToken accessToken){
        getEditor(context).putString(ACCESS_TOKEN, GGson.toJson(accessToken)).commit();
    }

    public static String getAuthorizationToken(Context context){
        AccessToken accessToken = getAccessToken(context);
        if(accessToken != null && accessToken.token_type.length() > 2){
            String type = Character.toUpperCase(accessToken.token_type.charAt(0)) + accessToken.token_type.substring(1);
            return type + " " + accessToken.access_token;
        }
        return "";
    }

}
