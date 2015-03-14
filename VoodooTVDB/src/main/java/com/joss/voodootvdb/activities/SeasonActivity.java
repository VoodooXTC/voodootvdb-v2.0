package com.joss.voodootvdb.activities;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.SeasonPagerAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.models.Episode.Episode;
import com.joss.voodootvdb.provider.episodes.EpisodesColumns;
import com.joss.voodootvdb.provider.episodes.EpisodesCursor;
import com.joss.voodootvdb.provider.episodes.EpisodesProvider;
import com.joss.voodootvdb.provider.episodes.EpisodesSelection;
import com.joss.voodootvdb.views.PagerSlidingTabStrip;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oak.util.OakUtils;

/**
 * Created by Jossay
 * Date: 3/14/2015
 * Time: 2:00 PM
 */
public class SeasonActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TRAKT_ID = "trakt_id";
    private static final String SEASON_NUMBER = "season_numeber";
    private static final int EPISODES_CALLBACK = 512;

    @InjectView(R.id.viewpager)
    ViewPager pager;
    @InjectView(R.id.viewpager_tabs)
    PagerSlidingTabStrip tabs;

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
        setToolbarTitle("Season");

        getLoaderManager().initLoader(EPISODES_CALLBACK, getIntent().getExtras(), this);

        Api.getEpisodes(this, getIntent().getIntExtra(TRAKT_ID, 0), getIntent().getIntExtra(SEASON_NUMBER, -1));
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
            List<Episode> episodes = EpisodesProvider.get(cursor);
            if(episodes.size() > 0){
                pager.setAdapter(new SeasonPagerAdapter(getSupportFragmentManager(), episodes));
                tabs.setTypeface(OakUtils.getStaticTypeFace(this, getString(R.string.font_roboto_thin)), 0);
                tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                tabs.setViewPager(pager);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
