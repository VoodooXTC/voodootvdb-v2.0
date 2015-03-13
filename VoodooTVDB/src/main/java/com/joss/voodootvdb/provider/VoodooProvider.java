package com.joss.voodootvdb.provider;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import com.joss.voodootvdb.BuildConfig;
import com.joss.voodootvdb.provider.episodes.EpisodesColumns;
import com.joss.voodootvdb.provider.episodes_watched.EpisodesWatchedColumns;
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

public class VoodooProvider extends ContentProvider {
    private static final String TAG = VoodooProvider.class.getSimpleName();

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = ".VoodooProvider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    public static final String QUERY_NOTIFY = "QUERY_NOTIFY";
    public static final String QUERY_GROUP_BY = "QUERY_GROUP_BY";

    private static final int URI_TYPE_EPISODES = 0;
    private static final int URI_TYPE_EPISODES_ID = 1;

    private static final int URI_TYPE_EPISODES_WATCHED = 2;
    private static final int URI_TYPE_EPISODES_WATCHED_ID = 3;

    private static final int URI_TYPE_MOVIES = 4;
    private static final int URI_TYPE_MOVIES_ID = 5;

    private static final int URI_TYPE_MOVIES_PEOPLE = 6;
    private static final int URI_TYPE_MOVIES_PEOPLE_ID = 7;

    private static final int URI_TYPE_MOVIES_RELATED = 8;
    private static final int URI_TYPE_MOVIES_RELATED_ID = 9;

    private static final int URI_TYPE_PERSON = 10;
    private static final int URI_TYPE_PERSON_ID = 11;

    private static final int URI_TYPE_PERSON_MOVIES = 12;
    private static final int URI_TYPE_PERSON_MOVIES_ID = 13;

    private static final int URI_TYPE_PERSON_SHOWS = 14;
    private static final int URI_TYPE_PERSON_SHOWS_ID = 15;

    private static final int URI_TYPE_SEASONS = 16;
    private static final int URI_TYPE_SEASONS_ID = 17;

    private static final int URI_TYPE_SHOWS = 18;
    private static final int URI_TYPE_SHOWS_ID = 19;

    private static final int URI_TYPE_SHOWS_PEOPLE = 20;
    private static final int URI_TYPE_SHOWS_PEOPLE_ID = 21;

    private static final int URI_TYPE_SHOWS_POPULAR = 22;
    private static final int URI_TYPE_SHOWS_POPULAR_ID = 23;

    private static final int URI_TYPE_SHOWS_RELATED = 24;
    private static final int URI_TYPE_SHOWS_RELATED_ID = 25;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, EpisodesColumns.TABLE_NAME, URI_TYPE_EPISODES);
        URI_MATCHER.addURI(AUTHORITY, EpisodesColumns.TABLE_NAME + "/#", URI_TYPE_EPISODES_ID);
        URI_MATCHER.addURI(AUTHORITY, EpisodesWatchedColumns.TABLE_NAME, URI_TYPE_EPISODES_WATCHED);
        URI_MATCHER.addURI(AUTHORITY, EpisodesWatchedColumns.TABLE_NAME + "/#", URI_TYPE_EPISODES_WATCHED_ID);
        URI_MATCHER.addURI(AUTHORITY, MoviesColumns.TABLE_NAME, URI_TYPE_MOVIES);
        URI_MATCHER.addURI(AUTHORITY, MoviesColumns.TABLE_NAME + "/#", URI_TYPE_MOVIES_ID);
        URI_MATCHER.addURI(AUTHORITY, MoviesPeopleColumns.TABLE_NAME, URI_TYPE_MOVIES_PEOPLE);
        URI_MATCHER.addURI(AUTHORITY, MoviesPeopleColumns.TABLE_NAME + "/#", URI_TYPE_MOVIES_PEOPLE_ID);
        URI_MATCHER.addURI(AUTHORITY, MoviesRelatedColumns.TABLE_NAME, URI_TYPE_MOVIES_RELATED);
        URI_MATCHER.addURI(AUTHORITY, MoviesRelatedColumns.TABLE_NAME + "/#", URI_TYPE_MOVIES_RELATED_ID);
        URI_MATCHER.addURI(AUTHORITY, PersonColumns.TABLE_NAME, URI_TYPE_PERSON);
        URI_MATCHER.addURI(AUTHORITY, PersonColumns.TABLE_NAME + "/#", URI_TYPE_PERSON_ID);
        URI_MATCHER.addURI(AUTHORITY, PersonMoviesColumns.TABLE_NAME, URI_TYPE_PERSON_MOVIES);
        URI_MATCHER.addURI(AUTHORITY, PersonMoviesColumns.TABLE_NAME + "/#", URI_TYPE_PERSON_MOVIES_ID);
        URI_MATCHER.addURI(AUTHORITY, PersonShowsColumns.TABLE_NAME, URI_TYPE_PERSON_SHOWS);
        URI_MATCHER.addURI(AUTHORITY, PersonShowsColumns.TABLE_NAME + "/#", URI_TYPE_PERSON_SHOWS_ID);
        URI_MATCHER.addURI(AUTHORITY, SeasonsColumns.TABLE_NAME, URI_TYPE_SEASONS);
        URI_MATCHER.addURI(AUTHORITY, SeasonsColumns.TABLE_NAME + "/#", URI_TYPE_SEASONS_ID);
        URI_MATCHER.addURI(AUTHORITY, ShowsColumns.TABLE_NAME, URI_TYPE_SHOWS);
        URI_MATCHER.addURI(AUTHORITY, ShowsColumns.TABLE_NAME + "/#", URI_TYPE_SHOWS_ID);
        URI_MATCHER.addURI(AUTHORITY, ShowsPeopleColumns.TABLE_NAME, URI_TYPE_SHOWS_PEOPLE);
        URI_MATCHER.addURI(AUTHORITY, ShowsPeopleColumns.TABLE_NAME + "/#", URI_TYPE_SHOWS_PEOPLE_ID);
        URI_MATCHER.addURI(AUTHORITY, ShowsPopularColumns.TABLE_NAME, URI_TYPE_SHOWS_POPULAR);
        URI_MATCHER.addURI(AUTHORITY, ShowsPopularColumns.TABLE_NAME + "/#", URI_TYPE_SHOWS_POPULAR_ID);
        URI_MATCHER.addURI(AUTHORITY, ShowsRelatedColumns.TABLE_NAME, URI_TYPE_SHOWS_RELATED);
        URI_MATCHER.addURI(AUTHORITY, ShowsRelatedColumns.TABLE_NAME + "/#", URI_TYPE_SHOWS_RELATED_ID);
    }

    private VoodooSQLiteOpenHelper mVoodooSQLiteOpenHelper;

    @Override
    public boolean onCreate() {
        mVoodooSQLiteOpenHelper = VoodooSQLiteOpenHelper.newInstance(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_EPISODES:
                return TYPE_CURSOR_DIR + EpisodesColumns.TABLE_NAME;
            case URI_TYPE_EPISODES_ID:
                return TYPE_CURSOR_ITEM + EpisodesColumns.TABLE_NAME;

            case URI_TYPE_EPISODES_WATCHED:
                return TYPE_CURSOR_DIR + EpisodesWatchedColumns.TABLE_NAME;
            case URI_TYPE_EPISODES_WATCHED_ID:
                return TYPE_CURSOR_ITEM + EpisodesWatchedColumns.TABLE_NAME;

            case URI_TYPE_MOVIES:
                return TYPE_CURSOR_DIR + MoviesColumns.TABLE_NAME;
            case URI_TYPE_MOVIES_ID:
                return TYPE_CURSOR_ITEM + MoviesColumns.TABLE_NAME;

            case URI_TYPE_MOVIES_PEOPLE:
                return TYPE_CURSOR_DIR + MoviesPeopleColumns.TABLE_NAME;
            case URI_TYPE_MOVIES_PEOPLE_ID:
                return TYPE_CURSOR_ITEM + MoviesPeopleColumns.TABLE_NAME;

            case URI_TYPE_MOVIES_RELATED:
                return TYPE_CURSOR_DIR + MoviesRelatedColumns.TABLE_NAME;
            case URI_TYPE_MOVIES_RELATED_ID:
                return TYPE_CURSOR_ITEM + MoviesRelatedColumns.TABLE_NAME;

            case URI_TYPE_PERSON:
                return TYPE_CURSOR_DIR + PersonColumns.TABLE_NAME;
            case URI_TYPE_PERSON_ID:
                return TYPE_CURSOR_ITEM + PersonColumns.TABLE_NAME;

            case URI_TYPE_PERSON_MOVIES:
                return TYPE_CURSOR_DIR + PersonMoviesColumns.TABLE_NAME;
            case URI_TYPE_PERSON_MOVIES_ID:
                return TYPE_CURSOR_ITEM + PersonMoviesColumns.TABLE_NAME;

            case URI_TYPE_PERSON_SHOWS:
                return TYPE_CURSOR_DIR + PersonShowsColumns.TABLE_NAME;
            case URI_TYPE_PERSON_SHOWS_ID:
                return TYPE_CURSOR_ITEM + PersonShowsColumns.TABLE_NAME;

            case URI_TYPE_SEASONS:
                return TYPE_CURSOR_DIR + SeasonsColumns.TABLE_NAME;
            case URI_TYPE_SEASONS_ID:
                return TYPE_CURSOR_ITEM + SeasonsColumns.TABLE_NAME;

            case URI_TYPE_SHOWS:
                return TYPE_CURSOR_DIR + ShowsColumns.TABLE_NAME;
            case URI_TYPE_SHOWS_ID:
                return TYPE_CURSOR_ITEM + ShowsColumns.TABLE_NAME;

            case URI_TYPE_SHOWS_PEOPLE:
                return TYPE_CURSOR_DIR + ShowsPeopleColumns.TABLE_NAME;
            case URI_TYPE_SHOWS_PEOPLE_ID:
                return TYPE_CURSOR_ITEM + ShowsPeopleColumns.TABLE_NAME;

            case URI_TYPE_SHOWS_POPULAR:
                return TYPE_CURSOR_DIR + ShowsPopularColumns.TABLE_NAME;
            case URI_TYPE_SHOWS_POPULAR_ID:
                return TYPE_CURSOR_ITEM + ShowsPopularColumns.TABLE_NAME;

            case URI_TYPE_SHOWS_RELATED:
                return TYPE_CURSOR_DIR + ShowsRelatedColumns.TABLE_NAME;
            case URI_TYPE_SHOWS_RELATED_ID:
                return TYPE_CURSOR_ITEM + ShowsRelatedColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (BuildConfig.DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        final String table = uri.getLastPathSegment();
        final long rowId = mVoodooSQLiteOpenHelper.getWritableDatabase().insert(table, null, values);
        String notify;
        if (rowId != -1 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri.buildUpon().appendEncodedPath(String.valueOf(rowId)).build();
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (BuildConfig.DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        final String table = uri.getLastPathSegment();
        final SQLiteDatabase db = mVoodooSQLiteOpenHelper.getWritableDatabase();
        int res = 0;
        db.beginTransaction();
        try {
            for (final ContentValues v : values) {
                final long id = db.insert(table, null, v);
                db.yieldIfContendedSafely();
                if (id != -1) {
                    res++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        String notify;
        if (res != 0 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return res;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        final QueryParams queryParams = getQueryParams(uri, selection);
        final int res = mVoodooSQLiteOpenHelper.getWritableDatabase().update(queryParams.table, values, queryParams.selection, selectionArgs);
        String notify;
        if (res != 0 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return res;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (BuildConfig.DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        final QueryParams queryParams = getQueryParams(uri, selection);
        final int res = mVoodooSQLiteOpenHelper.getWritableDatabase().delete(queryParams.table, queryParams.selection, selectionArgs);
        String notify;
        if (res != 0 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return res;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final String groupBy = uri.getQueryParameter(QUERY_GROUP_BY);
        if (BuildConfig.DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + groupBy);
        final QueryParams queryParams = getQueryParams(uri, selection);
        final Cursor res = mVoodooSQLiteOpenHelper.getReadableDatabase().query(queryParams.table, projection, queryParams.selection, selectionArgs, groupBy,
                null, sortOrder == null ? queryParams.orderBy : sortOrder);
        res.setNotificationUri(getContext().getContentResolver(), uri);
        return res;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        SQLiteDatabase db = mVoodooSQLiteOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            int numOperations = operations.size();
            ContentProviderResult[] results = new ContentProviderResult[numOperations];
            int i = 0;
            for (ContentProviderOperation operation : operations) {
                results[i] = operation.apply(this, results, i);
                if (operation.isYieldAllowed()) {
                    db.yieldIfContendedSafely();
                }
                i++;
            }
            db.setTransactionSuccessful();
            return results;
        } finally {
            db.endTransaction();
        }
    }

    private static class QueryParams {
        public String table;
        public String selection;
        public String orderBy;
    }

    private QueryParams getQueryParams(Uri uri, String selection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_EPISODES:
            case URI_TYPE_EPISODES_ID:
                res.table = EpisodesColumns.TABLE_NAME;
                res.orderBy = EpisodesColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_EPISODES_WATCHED:
            case URI_TYPE_EPISODES_WATCHED_ID:
                res.table = EpisodesWatchedColumns.TABLE_NAME;
                res.orderBy = EpisodesWatchedColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIES:
            case URI_TYPE_MOVIES_ID:
                res.table = MoviesColumns.TABLE_NAME;
                res.orderBy = MoviesColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIES_PEOPLE:
            case URI_TYPE_MOVIES_PEOPLE_ID:
                res.table = MoviesPeopleColumns.TABLE_NAME;
                res.orderBy = MoviesPeopleColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIES_RELATED:
            case URI_TYPE_MOVIES_RELATED_ID:
                res.table = MoviesRelatedColumns.TABLE_NAME;
                res.orderBy = MoviesRelatedColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PERSON:
            case URI_TYPE_PERSON_ID:
                res.table = PersonColumns.TABLE_NAME;
                res.orderBy = PersonColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PERSON_MOVIES:
            case URI_TYPE_PERSON_MOVIES_ID:
                res.table = PersonMoviesColumns.TABLE_NAME;
                res.orderBy = PersonMoviesColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_PERSON_SHOWS:
            case URI_TYPE_PERSON_SHOWS_ID:
                res.table = PersonShowsColumns.TABLE_NAME;
                res.orderBy = PersonShowsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SEASONS:
            case URI_TYPE_SEASONS_ID:
                res.table = SeasonsColumns.TABLE_NAME;
                res.orderBy = SeasonsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SHOWS:
            case URI_TYPE_SHOWS_ID:
                res.table = ShowsColumns.TABLE_NAME;
                res.orderBy = ShowsColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SHOWS_PEOPLE:
            case URI_TYPE_SHOWS_PEOPLE_ID:
                res.table = ShowsPeopleColumns.TABLE_NAME;
                res.orderBy = ShowsPeopleColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SHOWS_POPULAR:
            case URI_TYPE_SHOWS_POPULAR_ID:
                res.table = ShowsPopularColumns.TABLE_NAME;
                res.orderBy = ShowsPopularColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_SHOWS_RELATED:
            case URI_TYPE_SHOWS_RELATED_ID:
                res.table = ShowsRelatedColumns.TABLE_NAME;
                res.orderBy = ShowsRelatedColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_EPISODES_ID:
            case URI_TYPE_EPISODES_WATCHED_ID:
            case URI_TYPE_MOVIES_ID:
            case URI_TYPE_MOVIES_PEOPLE_ID:
            case URI_TYPE_MOVIES_RELATED_ID:
            case URI_TYPE_PERSON_ID:
            case URI_TYPE_PERSON_MOVIES_ID:
            case URI_TYPE_PERSON_SHOWS_ID:
            case URI_TYPE_SEASONS_ID:
            case URI_TYPE_SHOWS_ID:
            case URI_TYPE_SHOWS_PEOPLE_ID:
            case URI_TYPE_SHOWS_POPULAR_ID:
            case URI_TYPE_SHOWS_RELATED_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = BaseColumns._ID + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = BaseColumns._ID + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }

    public static Uri notify(Uri uri, boolean notify) {
        return uri.buildUpon().appendQueryParameter(QUERY_NOTIFY, String.valueOf(notify)).build();
    }

    public static Uri groupBy(Uri uri, String groupBy) {
        return uri.buildUpon().appendQueryParameter(QUERY_GROUP_BY, groupBy).build();
    }
}
