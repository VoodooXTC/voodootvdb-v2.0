package com.joss.voodootvdb.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.joss.voodootvdb.BuildConfig;
import com.joss.voodootvdb.provider.shows.ShowsColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;

public class VoodooSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = VoodooSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "voodoo.db";
    private static final int DATABASE_VERSION = 1;

    // @formatter:off
    private static final String SQL_CREATE_TABLE_SHOWS = "CREATE TABLE IF NOT EXISTS "
            + ShowsColumns.TABLE_NAME + " ( "
            + ShowsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShowsColumns.TITLE + " TEXT, "
            + ShowsColumns.TRAKT_ID + " INTEGER, "
            + ShowsColumns.IMDB_ID + " TEXT, "
            + ShowsColumns.FIRST_AIRED + " TEXT, "
            + ShowsColumns.COUNTRY + " TEXT, "
            + ShowsColumns.STATUS + " TEXT, "
            + ShowsColumns.RATING + " REAL, "
            + ShowsColumns.UPDATED_AT + " TEXT, "
            + ShowsColumns.LANGUAGE + " TEXT, "
            + ShowsColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_SHOWS_POPULAR = "CREATE TABLE IF NOT EXISTS "
            + ShowsPopularColumns.TABLE_NAME + " ( "
            + ShowsPopularColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShowsPopularColumns.TITLE + " TEXT, "
            + ShowsPopularColumns.TRAKT_ID + " INTEGER, "
            + ShowsPopularColumns.IMDB_ID + " TEXT, "
            + ShowsPopularColumns.FIRST_AIRED + " TEXT, "
            + ShowsPopularColumns.COUNTRY + " TEXT, "
            + ShowsPopularColumns.STATUS + " TEXT, "
            + ShowsPopularColumns.RATING + " REAL, "
            + ShowsPopularColumns.UPDATED_AT + " TEXT, "
            + ShowsPopularColumns.LANGUAGE + " TEXT, "
            + ShowsPopularColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    // @formatter:on

    public static VoodooSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */

    private static VoodooSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new VoodooSQLiteOpenHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    private VoodooSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /*
     * Post Honeycomb.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static VoodooSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new VoodooSQLiteOpenHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private VoodooSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        db.execSQL(SQL_CREATE_TABLE_SHOWS);
        db.execSQL(SQL_CREATE_TABLE_SHOWS_POPULAR);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (BuildConfig.DEBUG) Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
    }
}
