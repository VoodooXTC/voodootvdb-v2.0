package com.joss.voodootvdb.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.lang.reflect.Type;

/**
 * Created by: jossayjacobo
 * Date: 3/3/15
 * Time: 5:46 PM
 */
public class GGson {

    private static Gson ggson;

    public static Gson getGson(){
        if(ggson == null)
            ggson = new Gson();

        return ggson;
    }

    public static String toJson(Object object) {
        return getGson().toJson(object);
    }

    public static <T> T fromJson(String s, Class<T> c) {
        try{
            return getGson().fromJson(s, c);
        }catch (JsonSyntaxException exception){
            return null;
        }
    }

    public static <T> T fromJson(String s, Type t) {
        return getGson().fromJson(s, t);
    }

    public static <T> T fromJson(BufferedReader reader, Type t) {
        return getGson().fromJson(reader, t);
    }

}
