package com.joss.voodootvdb.Dumpers;

import android.content.Context;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.joss.voodootvdb.DataStore;
import com.joss.voodootvdb.utils.GGson;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by: jossayjacobo
 * Date: 4/4/15
 * Time: 12:24 PM
 */
public class UsersDumper implements DumperPluginsProvider {

    Context context;

    public UsersDumper(Context context){
        this.context = context;
    }

    @Override
    public Iterable<DumperPlugin> get() {
        ArrayList<DumperPlugin> plugins = new ArrayList<DumperPlugin>();
        for (DumperPlugin defaultPlugin :
                Stetho.defaultDumperPluginsProvider(context).get()) {
            plugins.add(defaultPlugin);
        }
        plugins.add(new MyDumperPlugin(context));
        return plugins;
    }

    public class MyDumperPlugin implements DumperPlugin {

        Context context;

        public MyDumperPlugin(Context context){
            this.context = context;
        }

        @Override
        public String getName() {
            return "voodoo";
        }

        @Override
        public void dump(DumperContext dumpContext) throws DumpException {
            final PrintStream output = dumpContext.getStdout();
            output.println(GGson.toJson(DataStore.getUserSettings(context), true));
        }
    }
}
