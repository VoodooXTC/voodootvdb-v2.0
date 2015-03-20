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
import com.joss.voodootvdb.provider.episodes.EpisodesColumns;
import com.joss.voodootvdb.provider.episodes_watched.EpisodesWatchedColumns;
import com.joss.voodootvdb.provider.list_items.ListItemsColumns;
import com.joss.voodootvdb.provider.lists.ListsColumns;
import com.joss.voodootvdb.provider.movies.MoviesColumns;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleColumns;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedColumns;
import com.joss.voodootvdb.provider.person.PersonColumns;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesColumns;
import com.joss.voodootvdb.provider.person_shows.PersonShowsColumns;
import com.joss.voodootvdb.provider.seasons.SeasonsColumns;
import com.joss.voodootvdb.provider.shows.ShowsColumns;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedColumns;

public class VoodooSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = VoodooSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "voodoo.db";
    private static final int DATABASE_VERSION = 1;

    // @formatter:off
    private static final String SQL_CREATE_TABLE_EPISODES = "CREATE TABLE IF NOT EXISTS "
            + EpisodesColumns.TABLE_NAME + " ( "
            + EpisodesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EpisodesColumns.SHOW_TRAKT_ID + " INTEGER, "
            + EpisodesColumns.EPISODE_TRAKT_ID + " INTEGER, "
            + EpisodesColumns.SEASON + " INTEGER, "
            + EpisodesColumns.NUMBER + " INTEGER, "
            + EpisodesColumns.FIRST_AIRED + " INTEGER, "
            + EpisodesColumns.UPDATED_AT + " INTEGER, "
            + EpisodesColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (EPISODE_TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_EPISODES_WATCHED = "CREATE TABLE IF NOT EXISTS "
            + EpisodesWatchedColumns.TABLE_NAME + " ( "
            + EpisodesWatchedColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EpisodesWatchedColumns.SHOW_TRAKT_ID + " INTEGER, "
            + EpisodesWatchedColumns.SEASON + " INTEGER, "
            + EpisodesWatchedColumns.NUMBER + " INTEGER, "
            + EpisodesWatchedColumns.COMPLETED + " INTEGER "
            + ", CONSTRAINT UNIQUE_SHOW_TRAKT_ID_SEASON_EPISODE_COMBINATION UNIQUE (SHOW_TRAKT_ID, SEASON, NUMBER) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_LIST_ITEMS = "CREATE TABLE IF NOT EXISTS "
            + ListItemsColumns.TABLE_NAME + " ( "
            + ListItemsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ListItemsColumns.LIST_TRAKT_ID + " INTEGER, "
            + ListItemsColumns.LISTED_AT + " INTEGER, "
            + ListItemsColumns.TYPE + " TEXT, "
            + ListItemsColumns.JSON + " TEXT "
            + " );";

    private static final String SQL_CREATE_TABLE_LISTS = "CREATE TABLE IF NOT EXISTS "
            + ListsColumns.TABLE_NAME + " ( "
            + ListsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ListsColumns.TRAKT_ID + " INTEGER, "
            + ListsColumns.NAME + " TEXT, "
            + ListsColumns.SLUG + " TEXT, "
            + ListsColumns.UPDATED_AT + " INTEGER, "
            + ListsColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_MOVIES = "CREATE TABLE IF NOT EXISTS "
            + MoviesColumns.TABLE_NAME + " ( "
            + MoviesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MoviesColumns.TITLE + " TEXT, "
            + MoviesColumns.YEAR + " INTEGER, "
            + MoviesColumns.TRAKT_ID + " INTEGER, "
            + MoviesColumns.IMDB_ID + " TEXT, "
            + MoviesColumns.RELEASED + " INTEGER, "
            + MoviesColumns.RATING + " REAL, "
            + MoviesColumns.UPDATED_AT + " INTEGER, "
            + MoviesColumns.LANGUAGE + " TEXT, "
            + MoviesColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_MOVIES_PEOPLE = "CREATE TABLE IF NOT EXISTS "
            + MoviesPeopleColumns.TABLE_NAME + " ( "
            + MoviesPeopleColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MoviesPeopleColumns.TRAKT_ID + " INTEGER, "
            + MoviesPeopleColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_MOVIES_RELATED = "CREATE TABLE IF NOT EXISTS "
            + MoviesRelatedColumns.TABLE_NAME + " ( "
            + MoviesRelatedColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MoviesRelatedColumns.MOVIE_TRAKT_ID + " INTEGER, "
            + MoviesRelatedColumns.RELATED_TRAKT_ID + " INTEGER "
            + " );";

    private static final String SQL_CREATE_TABLE_PERSON = "CREATE TABLE IF NOT EXISTS "
            + PersonColumns.TABLE_NAME + " ( "
            + PersonColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PersonColumns.NAME + " TEXT, "
            + PersonColumns.TRAKT_ID + " INTEGER, "
            + PersonColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_PERSON_MOVIES = "CREATE TABLE IF NOT EXISTS "
            + PersonMoviesColumns.TABLE_NAME + " ( "
            + PersonMoviesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PersonMoviesColumns.TRAKT_ID + " INTEGER, "
            + PersonMoviesColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_PERSON_SHOWS = "CREATE TABLE IF NOT EXISTS "
            + PersonShowsColumns.TABLE_NAME + " ( "
            + PersonShowsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PersonShowsColumns.TRAKT_ID + " INTEGER, "
            + PersonShowsColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_SEASONS = "CREATE TABLE IF NOT EXISTS "
            + SeasonsColumns.TABLE_NAME + " ( "
            + SeasonsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SeasonsColumns.SHOW_TRAKT_ID + " INTEGER, "
            + SeasonsColumns.SEASON_TRAKT_ID + " INTEGER, "
            + SeasonsColumns.NUMBER + " INTEGER, "
            + SeasonsColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (SEASON_TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_SHOWS = "CREATE TABLE IF NOT EXISTS "
            + ShowsColumns.TABLE_NAME + " ( "
            + ShowsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShowsColumns.TITLE + " TEXT, "
            + ShowsColumns.TRAKT_ID + " INTEGER, "
            + ShowsColumns.IMDB_ID + " TEXT, "
            + ShowsColumns.FIRST_AIRED + " INTEGER, "
            + ShowsColumns.COUNTRY + " TEXT, "
            + ShowsColumns.STATUS + " TEXT, "
            + ShowsColumns.RATING + " REAL, "
            + ShowsColumns.UPDATED_AT + " INTEGER, "
            + ShowsColumns.LANGUAGE + " TEXT, "
            + ShowsColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_SHOWS_PEOPLE = "CREATE TABLE IF NOT EXISTS "
            + ShowsPeopleColumns.TABLE_NAME + " ( "
            + ShowsPeopleColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShowsPeopleColumns.TRAKT_ID + " INTEGER, "
            + ShowsPeopleColumns.JSON + " TEXT "
            + ", CONSTRAINT UNIQUE_TRAKT_ID UNIQUE (TRAKT_ID) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_SHOWS_POPULAR = "CREATE TABLE IF NOT EXISTS "
            + ShowsPopularColumns.TABLE_NAME + " ( "
            + ShowsPopularColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShowsPopularColumns.SHOW_TRAKT_ID + " INTEGER "
            + " );";

    private static final String SQL_CREATE_TABLE_SHOWS_RELATED = "CREATE TABLE IF NOT EXISTS "
            + ShowsRelatedColumns.TABLE_NAME + " ( "
            + ShowsRelatedColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ShowsRelatedColumns.SHOW_TRAKT_ID + " INTEGER, "
            + ShowsRelatedColumns.RELATED_TRAKT_ID + " INTEGER "
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
        db.execSQL(SQL_CREATE_TABLE_EPISODES);
        db.execSQL(SQL_CREATE_TABLE_EPISODES_WATCHED);
        db.execSQL(SQL_CREATE_TABLE_LIST_ITEMS);
        db.execSQL(SQL_CREATE_TABLE_LISTS);
        db.execSQL(SQL_CREATE_TABLE_MOVIES);
        db.execSQL(SQL_CREATE_TABLE_MOVIES_PEOPLE);
        db.execSQL(SQL_CREATE_TABLE_MOVIES_RELATED);
        db.execSQL(SQL_CREATE_TABLE_PERSON);
        db.execSQL(SQL_CREATE_TABLE_PERSON_MOVIES);
        db.execSQL(SQL_CREATE_TABLE_PERSON_SHOWS);
        db.execSQL(SQL_CREATE_TABLE_SEASONS);
        db.execSQL(SQL_CREATE_TABLE_SHOWS);
        db.execSQL(SQL_CREATE_TABLE_SHOWS_PEOPLE);
        db.execSQL(SQL_CREATE_TABLE_SHOWS_POPULAR);
        db.execSQL(SQL_CREATE_TABLE_SHOWS_RELATED);
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
