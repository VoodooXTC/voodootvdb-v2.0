package com.joss.voodootvdb.search;

import android.app.SearchManager;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Search.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 9:51 PM
 */
public class SuggestionProvider extends SearchRecentSuggestionsProvider{

    public static final String AUTHORITY = SuggestionProvider.class.getName();
    public static final int MODE = DATABASE_MODE_QUERIES;
    private static final String ACTION_VIEW = "android.intent.action.VIEW";

    private static final String[] COLUMNS = {
            "_id",
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_TEXT_2,
            SearchManager.SUGGEST_COLUMN_ICON_1,
            SearchManager.SUGGEST_COLUMN_INTENT_EXTRA_DATA,
            SearchManager.SUGGEST_COLUMN_INTENT_ACTION,
            SearchManager.SUGGEST_COLUMN_SHORTCUT_ID
    };

    public SuggestionProvider(){
        setupSuggestions(AUTHORITY, MODE);
    }

    @Override
    public Cursor query(@Nullable Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){

        //Query the user entered into the search bar
        String query = selectionArgs[0];

        // MatrixCursor to return results
        MatrixCursor cursor = new MatrixCursor(COLUMNS);

        if(query == null || query.length() < 4){
            return null;
        }else{
            List<Search> searchResults = new ArrayList<>();
            searchResults.addAll(ApiService.getApi(getContext()).search(query, Search.TYPE_SHOW, 1, 3));
            searchResults.addAll(ApiService.getApi(getContext()).search(query, Search.TYPE_MOVIE, 1, 3));
            searchResults.addAll(ApiService.getApi(getContext()).search(query, Search.TYPE_PERSON, 1, 3));

            for(Search s : searchResults){
                // Add a row to the cursor
                cursor.addRow(createRow(s.getId(), s.getTitle(), s.type, s.getIcon()));
            }
        }

        return cursor;
    }

    private Object[] createRow(Integer id, String title, String subtitle, int icon){
        return new Object[]{
                id,                                             // id
                title,	                                        // Title of suggestion
                subtitle,	                                    // Sub-Title of suggestion
                icon,                                           // data to be sent when select from list as query
                id,                                             // data to be sent as extra string when select from list as result
                ACTION_VIEW,                                    // action to be created
                SearchManager.SUGGEST_NEVER_MAKE_SHORTCUT };
    }
}
