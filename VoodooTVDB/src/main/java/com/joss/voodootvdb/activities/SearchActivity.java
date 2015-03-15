package com.joss.voodootvdb.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.fragments.SearchFragment;
import com.joss.voodootvdb.interfaces.ToolbarListener;
import com.joss.voodootvdb.utils.GGson;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 12:28 AM
 */
public class SearchActivity extends BaseActivity implements ToolbarListener {

    public static final String TAG = SearchActivity.class.getSimpleName();

    @InjectView(R.id.search_content)
    FrameLayout content;

    SearchFragment searchFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent(), savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent, null);
    }

    private void handleIntent(Intent intent, Bundle savedInstanceState) {
        switch (intent.getAction()){
            case Intent.ACTION_SEARCH:
                String query = intent.getStringExtra(SearchManager.QUERY);
                if(searchFragment == null){
                    setContentView(R.layout.activity_search);
                    ButterKnife.inject(this);
                    setupToolbar(toolbar);

                    searchFragment = savedInstanceState == null
                            ? SearchFragment.getInstance(query)
                            : (SearchFragment) getSupportFragmentManager().findFragmentByTag(SearchFragment.TAG);

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(content.getId(), searchFragment, SearchFragment.TAG);
                    ft.commit();
                }else{
                    searchFragment.search(query);
                }

                break;

            case Intent.ACTION_VIEW:
                Search s = GGson.fromJson(intent.getStringExtra(SearchManager.EXTRA_DATA_KEY), Search.class);
                if(s != null){
                    switch (s.type){
                        case Search.TYPE_PERSON:
                            PersonActivity.startActivity(this, s.getId());
                            break;

                        case Search.TYPE_SHOW:
                            ShowActivity.startActivity(this, s.getId());
                            break;

                        case Search.TYPE_MOVIE:
                            MovieActivity.startActivity(this, s.getId());
                            break;
                    }
                }
                finish();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        getSupportFragmentManager().putFragment(savedInstanceState, SearchFragment.TAG, searchFragment);
    }

    @Override
    public void onSetToolbarTitles(String title, String subtitle) {
        setToolbarTitles(title, subtitle);
    }
}
