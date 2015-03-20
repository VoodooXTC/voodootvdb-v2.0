package com.joss.voodootvdb.activities;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.SeasonPagerAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.provider.episodes.EpisodesColumns;
import com.joss.voodootvdb.provider.episodes.EpisodesCursor;
import com.joss.voodootvdb.provider.episodes.EpisodesProvider;
import com.joss.voodootvdb.provider.episodes.EpisodesSelection;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;
import com.joss.voodootvdb.views.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oak.util.OakUtils;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 2:00 PM
 */
public class SeasonActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    private static final String TRAKT_ID = "trakt_id";
    private static final String SEASON_NUMBER = "season_numeber";
    private static final int EPISODES_CALLBACK = 512;

    @InjectView(R.id.viewpager)
    ViewPager pager;
    @InjectView(R.id.viewpager_tabs)
    PagerSlidingTabStrip tabs;
    @InjectView(R.id.season_error_view)
    ErrorView errorView;
    @InjectView(R.id.season_loading)
    LoadingView loadingView;

    LocalBroadcastManager broadcastManager;
    ApiReceiver apiReceiver;
    List<Episode> episodes;

    public static void startActivity(Context context, int traktId, int seasonNumber){
        Intent i = new Intent(context, SeasonActivity.class);
        i.putExtra(TRAKT_ID, traktId);
        i.putExtra(SEASON_NUMBER, seasonNumber);
        context.startActivity(i);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);
        ButterKnife.inject(this);

        setupToolbar(toolbar);
        setToolbarTitle(getString(R.string.season));

        episodes = new ArrayList<>();
        broadcastManager = LocalBroadcastManager.getInstance(this);
        apiReceiver = new ApiReceiver();

        showLoading();
        errorView.setOnClickListener(this);

        getLoaderManager().initLoader(EPISODES_CALLBACK, getIntent().getExtras(), this);
        Api.getEpisodes(this, getIntent().getIntExtra(TRAKT_ID, 0), getIntent().getIntExtra(SEASON_NUMBER, -1));
    }

    @Override
    public void onResume() {
        super.onResume();
        broadcastManager.registerReceiver(apiReceiver, new IntentFilter(ApiService.BROADCAST));
    }

    @Override
    public void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(apiReceiver);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        EpisodesSelection where = new EpisodesSelection();
        where.showTraktId(args.getInt(TRAKT_ID, 0)).and().season(args.getInt(SEASON_NUMBER, -1));
        return new CursorLoader(this,
                EpisodesColumns.CONTENT_URI,
                EpisodesColumns.FULL_PROJECTION,
                where.sel(),
                where.args(),
                EpisodesColumns.NUMBER + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            EpisodesCursor cursor = new EpisodesCursor(data);
            episodes = EpisodesProvider.get(cursor);
            if(episodes.size() > 0){
                showContent();
                pager.setAdapter(new SeasonPagerAdapter(getSupportFragmentManager(), episodes));
                tabs.setTypeface(OakUtils.getStaticTypeFace(this, getString(R.string.font_roboto_thin)), 0);
                tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                tabs.setViewPager(pager);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.season_error_view:
                showLoading();
                Api.getEpisodes(this, getIntent().getIntExtra(TRAKT_ID, 0), getIntent().getIntExtra(SEASON_NUMBER, -1));
                break;
        }
    }

    private class ApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int requestType = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            int status = intent.getIntExtra(ApiService.RESULT_STATUS, ApiService.RESULT_ERROR);

            if(requestType == ApiService.REQUEST_EPISODES
                    && status == ApiService.RESULT_ERROR
                    && episodes.size() == 0)
                showError();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void showContent(){
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    private void showLoading(){
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    private void showError(){
        errorView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }
}
