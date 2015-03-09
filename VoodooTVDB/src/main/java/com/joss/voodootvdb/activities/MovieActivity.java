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
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Movie.Movie;
import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.VoodooClickListener;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.provider.movies.MoviesColumns;
import com.joss.voodootvdb.provider.movies.MoviesCursor;
import com.joss.voodootvdb.provider.movies.MoviesProvider;
import com.joss.voodootvdb.provider.movies.MoviesSelection;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleColumns;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleCursor;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleProvider;
import com.joss.voodootvdb.provider.movies_people.MoviesPeopleSelection;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedColumns;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedCursor;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedProvider;
import com.joss.voodootvdb.provider.movies_related.MoviesRelatedSelection;
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
import com.joss.voodootvdb.utils.DateFormatter;
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
public class MovieActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener, VoodooClickListener {

    public static final String TAG = MovieActivity.class.getSimpleName();
    public static final String ID = "id";

    private static final int MOVIE_CALLBACK = 87656;
    private static final int MOVIES_RELATED_CALLBACK = 87657;
    private static final int MOVIES_PEOPLE_CALLBACK = 87658;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.movie_image)
    ImageView image;
    @InjectView(R.id.movie_play_button)
    FloatingActionButton playButton;
    @InjectView(R.id.movie_title)
    TextView title;
    @InjectView(R.id.movie_rating)
    RatingBar ratingBar;
    @InjectView(R.id.movie_info)
    TextView info;
    @InjectView(R.id.movie_genre)
    TextView genre;
    @InjectView(R.id.movie_description)
    TextView description;
    @InjectView(R.id.movie_status)
    TextView released;
    @InjectView(R.id.movie_related_title)
    TextView relatedTitle;
    @InjectView(R.id.movie_related_container)
    VoodooHorizontalScrollView relatedContainer;
    @InjectView(R.id.movie_people_container)
    VoodooHorizontalScrollView peopleContainer;
    @InjectView(R.id.movie_loading)
    LoadingView loadingView;
    @InjectView(R.id.movie_error)
    ErrorView errorView;

    LocalBroadcastManager broadcastManager;
    ApiReceiver apiReceiver;
    Movie movie;

    public static void startActivity(Context c, Movie movie) {
        Intent i = new Intent(c, MovieActivity.class);
        i.putExtra(ID, movie.getId());
        c.startActivity(i);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.inject(this);

        setupToolbar(toolbar);
        setToolbarTitle(getString(R.string.movie));

        playButton.setOnClickListener(this);
        errorView.setOnClickListener(this);
        showLoading();

        getLoaderManager().initLoader(MOVIE_CALLBACK, getIntent().getExtras(), this);
        getLoaderManager().initLoader(MOVIES_RELATED_CALLBACK, getIntent().getExtras(), this);
        getLoaderManager().initLoader(MOVIES_PEOPLE_CALLBACK, getIntent().getExtras(), this);

        Api.getMovie(this, getIntent().getIntExtra(ID, 0));
        Api.getMoviesRelated(this, getIntent().getIntExtra(ID, 0));
        Api.getMoviesPeople(this, getIntent().getIntExtra(ID, 0));

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
            case R.id.movie_error:
                showLoading();
                Api.getShow(this, getIntent().getIntExtra(ID, 0));
                break;
            case R.id.movie_play_button:
                onTrailerClicked(movie.getTrailer());
                break;
        }
    }

    @Override
    public void onShowClicked(Show show) {

    }

    @Override
    public void onShowMenuClicked(Show show) {
    }

    @Override
    public void onMovieMenuClicked(Movie movie) {
        // TODO maybe move this into the actual view??
        Utils.toast(this, movie.getTitle() + " : Movie Menu Clicked");
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
        // TODO
    }

    private class ApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int requestType = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            int status = intent.getIntExtra(ApiService.RESULT_STATUS, ApiService.RESULT_ERROR);

            if(requestType == ApiService.REQUEST_MOVIE
                    && status == ApiService.RESULT_ERROR
                    && movie == null)
                showError();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        int traktId = args.getInt(ID, 0);
        switch (id){
            case MOVIE_CALLBACK:
                MoviesSelection where = new MoviesSelection();
                where.traktId(traktId);
                return new CursorLoader(this,
                        MoviesColumns.CONTENT_URI,
                        MoviesColumns.FULL_PROJECTION,
                        where.sel(),
                        where.args(),
                        MoviesColumns.DEFAULT_ORDER + " LIMIT 1");

            case MOVIES_RELATED_CALLBACK:
                MoviesRelatedSelection whereRelated = new MoviesRelatedSelection();
                whereRelated.movieTraktId(traktId);
                return new CursorLoader(this,
                        MoviesRelatedColumns.CONTENT_URI,
                        MoviesRelatedColumns.FULL_PROJECTION,
                        whereRelated.sel(),
                        whereRelated.args(),
                        MoviesRelatedColumns.DEFAULT_ORDER + " LIMIT 5");

            case MOVIES_PEOPLE_CALLBACK:
                MoviesPeopleSelection wherePeople = new MoviesPeopleSelection();
                wherePeople.traktId(traktId);
                return new CursorLoader(this,
                        MoviesPeopleColumns.CONTENT_URI,
                        MoviesPeopleColumns.FULL_PROJECTION,
                        wherePeople.sel(),
                        wherePeople.args(),
                        MoviesPeopleColumns.DEFAULT_ORDER + " LIMIT 1");
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            switch (loader.getId()){
                case MOVIE_CALLBACK:
                    MoviesCursor cursor = new MoviesCursor(data);
                    movie = MoviesProvider.get(cursor);

                    if(movie != null) {
                        showContent();
                        setContent(movie);
                    }
                    break;
                case MOVIES_RELATED_CALLBACK:
                    MoviesRelatedCursor cursorRelated = new MoviesRelatedCursor(data);
                    List<VoodooItem> relatedMovies = MoviesRelatedProvider.getVoodooItems(this, VoodooCardView.TYPE_MOVIE, cursorRelated);
                    if(relatedMovies.size() > 0) {
                        relatedContainer.setListener(this);
                        relatedContainer.setItems(null, relatedMovies);
                    }
                    break;

                case MOVIES_PEOPLE_CALLBACK:
                    MoviesPeopleCursor cursorPeople = new MoviesPeopleCursor(data);
                    List<VoodooItem> cast = MoviesPeopleProvider.getVoodooItems(VoodooCardView.TYPE_CAST, getString(R.string.staring), cursorPeople);
                    if(cast.size() > 0) {
                        peopleContainer.setListener(this);
                        peopleContainer.setItems(getString(R.string.staring), cast);
                    }
                    break;
            }
        }
    }

    private void setContent(Movie movie) {
        String url = movie.getImages().getFanart().getFull();
        if(!url.isEmpty()){
            Picasso.with(this).load(url).fit().centerCrop().into(image);
        }
        playButton.setVisibility(!movie.getTrailer().isEmpty() ? View.VISIBLE : View.GONE);
        title.setText(movie.getTitle());
        ratingBar.setRating((float) (movie.getRating() / 2));
        info.setText(movie.getYear() + " " + movie.getCertification() + " " + movie.getRuntime() + " Mins");
        genre.setText(buildGenresString(movie.getGenres()));
        description.setText(movie.getOverview());
        released.setText(DateFormatter.format(movie.getReleased()));
        relatedTitle.setText(buildRelatedString(movie));
    }

    private SpannableStringBuilder buildRelatedString(Movie movie) {
        String titlesRelatedTo = getString(R.string.show_titles_related_to);

        Typeface light = OakUtils.getStaticTypeFace(this, getString(R.string.font_roboto_light));
        Typeface lightItalic = OakUtils.getStaticTypeFace(this, getString(R.string.font_roboto_light_italic));

        String fullText = titlesRelatedTo + " " + movie.getTitle();
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
