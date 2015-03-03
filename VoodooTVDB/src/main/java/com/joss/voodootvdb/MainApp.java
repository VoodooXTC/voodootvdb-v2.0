package com.joss.voodootvdb;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:13 PM
 */
public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void attachBaseContext(Context base){
        super.attachBaseContext(base);

        MultiDex.install(base);
    }
}
