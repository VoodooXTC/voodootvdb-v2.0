package com.joss.voodootvdb.activities;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.Season.Season;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.VoodooClickListener;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.provider.seasons.SeasonsColumns;
import com.joss.voodootvdb.provider.seasons.SeasonsCursor;
import com.joss.voodootvdb.provider.seasons.SeasonsProvider;
import com.joss.voodootvdb.provider.seasons.SeasonsSelection;
import com.joss.voodootvdb.provider.shows.ShowsColumns;
import com.joss.voodootvdb.provider.shows.ShowsCursor;
import com.joss.voodootvdb.provider.shows.ShowsProvider;
import com.joss.voodootvdb.provider.shows.ShowsSelection;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleColumns;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleCursor;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleProvider;
import com.joss.voodootvdb.provider.shows_people.ShowsPeopleSelection;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedColumns;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedCursor;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedProvider;
import com.joss.voodootvdb.provider.shows_related.ShowsRelatedSelection;
import com.joss.voodootvdb.utils.CustomTypefaceSpan;
import com.joss.voodootvdb.utils.Utils;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;
import com.joss.voodootvdb.views.VoodooCardView;
import com.joss.voodootvdb.views.VoodooHorizontalScrollView;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import oak.util.OakUtils;

/**
 * Created by jossayjacobo
 * Date: 3/4/15
 * Time: 10:01 AM
 */
public class ShowActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        View.OnClickListener, VoodooClickListener {

    public static final String TAG = ShowActivity.class.getSimpleName();
    public static final String ID = "id";

    private static final int SHOW_CALLBACK = 32145;
    private static final int SHOW_RELATED_CALLBACK = 32146;
    private static final int SHOW_PEOPLE_CALLBACK = 32147;
    private static final int SHOW_SEASONS_CALLBACK = 32148;

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
    @InjectView(R.id.show_status)
    TextView status;
    @InjectView(R.id.show_related_title)
    TextView relatedTitle;
    @InjectView(R.id.show_related_container)
    VoodooHorizontalScrollView relatedContainer;
    @InjectView(R.id.show_people_container)
    VoodooHorizontalScrollView peopleContainer;
    @InjectView(R.id.show_loading)
    LoadingView loadingView;
    @InjectView(R.id.show_error)
    ErrorView errorView;

    LocalBroadcastManager broadcastManager;
    ApiReceiver apiReceiver;
    Show show;

    public static void startActivity(Context c, Show show) {
        Intent i = new Intent(c, ShowActivity.class);
        i.putExtra(ID, show.getId());
        c.startActivity(i);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.inject(this);

        setupToolbar(toolbar);
        setToolbarTitle(getString(R.string.show));

        playButton.setOnClickListener(this);
        errorView.setOnClickListener(this);
        showLoading();

        getLoaderManager().initLoader(SHOW_CALLBACK, getIntent().getExtras(), this);
        getLoaderManager().initLoader(SHOW_RELATED_CALLBACK, getIntent().getExtras(), this);
        getLoaderManager().initLoader(SHOW_PEOPLE_CALLBACK, getIntent().getExtras(), this);
        getLoaderManager().initLoader(SHOW_SEASONS_CALLBACK, getIntent().getExtras(), this);

        int traktId = getIntent().getIntExtra(ID, 0);
        Api.getShow(this, traktId);
        Api.getShowRelated(this, traktId);
        Api.getShowPeople(this, traktId);
        Api.getSeasons(this, traktId);

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
                Api.getShow(this, getIntent().getIntExtra(ID, 0));
                break;

            case R.id.show_play_button:
                onTrailerClicked(show.getTrailer());
                break;
        }
    }

    @Override
    public void onShowClicked(Show show) {
        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra(ShowActivity.ID, show.getIds().getTrakt());
        startActivity(intent);
    }

    @Override
    public void onShowMenuClicked(Show show) {
        // TODO maybe move this into the actual view??
        Utils.toast(this, show.getTitle() + " : Show Menu Clicked");
    }

    @Override
    public void onMovieMenuClicked(Movie movie) {

    }

    @Override
    public void onTrailerClicked(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onCastClicked(Cast cast) {
        PersonActivity.startActivity(this, cast.getId());
    }

    @Override
    public void onMovieClicked(Movie movie) {

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
        int traktId = args.getInt(ID, 0);
        switch (id){
            case SHOW_CALLBACK:
                ShowsSelection where = new ShowsSelection();
                where.traktId(traktId);
                return new CursorLoader(this,
                        ShowsColumns.CONTENT_URI,
                        ShowsColumns.FULL_PROJECTION,
                        where.sel(),
                        where.args(),
                        ShowsColumns.DEFAULT_ORDER + " LIMIT 1");

            case SHOW_RELATED_CALLBACK:
                ShowsRelatedSelection whereRelated = new ShowsRelatedSelection();
                whereRelated.showTraktId(traktId);
                return new CursorLoader(this,
                        ShowsRelatedColumns.CONTENT_URI,
                        ShowsRelatedColumns.FULL_PROJECTION,
                        whereRelated.sel(),
                        whereRelated.args(),
                        ShowsRelatedColumns.DEFAULT_ORDER + " LIMIT 5");

            case SHOW_PEOPLE_CALLBACK:
                ShowsPeopleSelection wherePeople = new ShowsPeopleSelection();
                wherePeople.traktId(traktId);
                return new CursorLoader(this,
                        ShowsPeopleColumns.CONTENT_URI,
                        ShowsPeopleColumns.FULL_PROJECTION,
                        wherePeople.sel(),
                        wherePeople.args(),
                        ShowsPeopleColumns.DEFAULT_ORDER + " LIMIT 1");

            case SHOW_SEASONS_CALLBACK:
                SeasonsSelection whereSeason = new SeasonsSelection();
                whereSeason.showTraktId(traktId);
                return new CursorLoader(this,
                        SeasonsColumns.CONTENT_URI,
                        SeasonsColumns.FULL_PROJECTION,
                        whereSeason.sel(),
                        whereSeason.args(),
                        SeasonsColumns.NUMBER + " ASC");
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            switch (loader.getId()){
                case SHOW_CALLBACK:
                    ShowsCursor cursor = new ShowsCursor(data);
                    show = ShowsProvider.get(cursor);

                    if(show != null) {
                        showContent();
                        setContent(show);
                    }
                    break;
                case SHOW_RELATED_CALLBACK:
                    ShowsRelatedCursor cursorRelated = new ShowsRelatedCursor(data);
                    List<VoodooItem> relatedShows = ShowsRelatedProvider.getVoodooItems(this, cursorRelated);
                    if(relatedShows.size() > 0) {
                        relatedContainer.setListener(this);
                        relatedContainer.setItems(null, relatedShows);
                    }
                    break;

                case SHOW_PEOPLE_CALLBACK:
                    ShowsPeopleCursor cursorPeople = new ShowsPeopleCursor(data);
                    List<VoodooItem> cast = ShowsPeopleProvider.getVoodooItems(VoodooCardView.TYPE_CAST, getString(R.string.staring), cursorPeople);
                    if(cast.size() > 0) {
                        peopleContainer.setListener(this);
                        peopleContainer.setItems(getString(R.string.staring), cast);
                    }
                    break;

                case SHOW_SEASONS_CALLBACK:
                    SeasonsCursor seasonsCursor = new SeasonsCursor(data);
                    List<Season> seasons = SeasonsProvider.get(seasonsCursor);
                    // TODO add an list item per season
                    for(Season s : seasons){
                        Log.e(TAG, "Season Number: " + s.getNumber() + " Episode Count: " + s.getEpisode_count());
                    }
                    break;
            }
        }
    }

    private void setContent(Show show) {
        String url = show.getImages().getFanart().getFull();
        if(!url.isEmpty()){
            Picasso.with(this).load(url).fit().centerCrop().into(image);
        }
        playButton.setVisibility(!show.getTrailer().isEmpty() ? View.VISIBLE : View.GONE);
        title.setText(show.getTitle());
        ratingBar.setRating((float) (show.getRating() / 2));
        info.setText(show.getYear() + " " + show.getCertification() + " " + show.getRuntime() + " Mins");
        genre.setText(buildGenresString(show.getGenres()));
        description.setText(show.getOverview());
        status.setText(show.getStatus());
        relatedTitle.setText(buildRelatedString(show));
    }

    private SpannableStringBuilder buildRelatedString(Show show) {
        String titlesRelatedTo = getString(R.string.show_titles_related_to);

        Typeface light = OakUtils.getStaticTypeFace(this, getString(R.string.font_roboto_light));
        Typeface lightItalic = OakUtils.getStaticTypeFace(this, getString(R.string.font_roboto_light_italic));

        String fullText = titlesRelatedTo + " " + show.getTitle();
        SpannableStringBuilder ss = new SpannableStringBuilder(fullText);
        ss.setSpan(new CustomTypefaceSpan("", light), 0, titlesRelatedTo.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss.setSpan(new CustomTypefaceSpan("", lightItalic), titlesRelatedTo.length(), fullText.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        return ss;
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
