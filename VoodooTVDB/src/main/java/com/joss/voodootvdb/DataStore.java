package com.joss.voodootvdb;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.joss.voodootvdb.api.models.Login.UserModel;
import com.joss.voodootvdb.utils.GGson;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:16 PM
 */
public class DataStore {

    private static String FIRST_LAUNCH = "first";
    private static String USER = "user";

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

    public static UserModel getUser(Context context){
        return GGson.fromJson(getPrefs(context).getString(USER, null), UserModel.class);
    }

    public static void persistUser(Context context, UserModel userModel){
        getEditor(context).putString(USER, GGson.toJson(userModel)).commit();
    }

}
