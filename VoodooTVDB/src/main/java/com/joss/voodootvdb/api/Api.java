package com.joss.voodootvdb.api;

import android.content.Context;
import android.content.Intent;

import com.joss.voodootvdb.api.models.Login.UserModel;
import com.joss.voodootvdb.utils.GGson;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 10:52 AM
 */
public class Api {

    public static void login(Context context, String username, String password){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_LOGIN);
        intent.putExtra(ApiService.ARGS_USER, GGson.toJson(new UserModel(username, password)));
        context.startService(intent);
    }

    public static void getPopularShows(Context context, String... extended){
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(ApiService.REQUEST_TYPE, ApiService.REQUEST_POPULAR_SHOWS);

        String extendedValues = "";
        for(String ex : extended){
            extendedValues += ex + ",";
        }
        intent.putExtra(ApiService.ARG_EXTENDED, extendedValues);
        context.startService(intent);
    }
}
