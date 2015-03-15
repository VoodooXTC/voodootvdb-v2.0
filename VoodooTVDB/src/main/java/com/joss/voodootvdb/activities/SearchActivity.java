package com.joss.voodootvdb.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 12:28 AM
 */
public class SearchActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }
}
