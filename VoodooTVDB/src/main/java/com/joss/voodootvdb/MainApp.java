package com.joss.voodootvdb;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.joss.voodootvdb.Dumpers.UsersDumper;
import com.squareup.picasso.Picasso;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:13 PM
 */
public class MainApp extends Application {

    public static final String TAG = MainApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .enableDumpapp(new UsersDumper(this))
                        .build());

        if(BuildConfig.DEBUG)
            Picasso.with(this).setIndicatorsEnabled(true);
    }

    @Override
    public void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
