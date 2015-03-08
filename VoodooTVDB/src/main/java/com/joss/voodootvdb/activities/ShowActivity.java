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
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.provider.shows.ShowsColumns;
import com.joss.voodootvdb.provider.shows.ShowsCursor;
import com.joss.voodootvdb.provider.shows.ShowsProvider;
import com.joss.voodootvdb.provider.shows.ShowsSelection;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jossayjacobo
 * Date: 3/4/15
 * Time: 10:01 AM
 */
public class ShowActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    public static final String TAG = ShowActivity.class.getSimpleName();
    public static final String ID = "id";

    private static final int SHOW_CALLBACK = 32145;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.show_image)
    ImageView image;
    @InjectView(R.id.show_play_button)
    FloatingActionButton playButton;
    @InjectView(R.id.show_title)
    TextView title;
    @InjectView(R.id.show_rating)
    RatingBar ratingBar;
    @InjectView(R.id.show_info)
    TextView info;
    @InjectView(R.id.show_genre)
    TextView genre;
    @InjectView(R.id.show_description)
    TextView description;
    @InjectView(R.id.show_actors)
    TextView actors;
    @InjectView(R.id.show_status)
    TextView status;
    @InjectView(R.id.show_loading)
    LoadingView loadingView;
    @InjectView(R.id.show_error)
    ErrorView errorView;

    LocalBroadcastManager broadcastManager;
    ApiReceiver apiReceiver;
    Show show;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.inject(this);

        setupToolbar(toolbar);
        setToolbarTitle(getString(R.string.show));

        errorView.setOnClickListener(this);
        showLoading();

        getLoaderManager().initLoader(SHOW_CALLBACK, getIntent().getExtras(), this);
        Api.getShow(this,
                getIntent().getIntExtra(ID, 0),
                ApiService.EXT_FULL,
                ApiService.EXT_IMAGES,
                ApiService.EXT_METADATA);

        broadcastManager = LocalBroadcastManager.getInstance(this);
        apiReceiver = new ApiReceiver();
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_error:
                showLoading();
                Api.getShow(this,
                        getIntent().getIntExtra(ID, 0),
                        ApiService.EXT_FULL,
                        ApiService.EXT_IMAGES,
                        ApiService.EXT_METADATA);
                break;
        }
    }

    private class ApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int requestType = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            int status = intent.getIntExtra(ApiService.RESULT_STATUS, ApiService.RESULT_ERROR);

            if(requestType == ApiService.REQUEST_SHOW
                    && status == ApiService.RESULT_ERROR
                    && show == null)
                showError();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        int traktId = getIntent().getIntExtra(ID, 0);
        ShowsSelection where = new ShowsSelection();
        where.traktId(traktId);

        return new CursorLoader(this,
                ShowsColumns.CONTENT_URI,
                ShowsColumns.FULL_PROJECTION,
                where.sel(),
                where.args(),
                ShowsColumns.DEFAULT_ORDER + " LIMIT 1");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            ShowsCursor cursor = new ShowsCursor(data);
            show = ShowsProvider.get(cursor);

            if(show != null) {
                showContent();
                setContent(show);
            }
        }
    }

    private void setContent(Show show) {
        Picasso.with(this).load(show.getImages().getFanart().getFull()).fit().centerCrop().into(image);
        playButton.setVisibility(!show.getTrailer().isEmpty() ? View.VISIBLE : View.GONE);
        title.setText(show.getTitle());
        ratingBar.setRating((float) (show.getRating() / 2));
        info.setText(show.getYear() + " " + show.getCertification() + " " + show.getRuntime() + " Mins");
        genre.setText(buildGenresString(show.getGenres()));
        description.setText(show.getOverview());
        status.setText(show.getStatus());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private String buildGenresString(List<String> genres) {
        String genreList = "";
        for(int i = 0; i < genres.size(); i++){
            String genre = genres.get(i);
            if(genre != null && genre.length() > 0){
                genreList += genre.substring(0, 1).toUpperCase() + genre.substring(1, genre.length());
                if(i != genres.size() - 1)
                    genreList += "|";
            }
        }
        return genreList;
    }

    public void showLoading(){
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    public void showError(){
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    public void showContent(){
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }
}
