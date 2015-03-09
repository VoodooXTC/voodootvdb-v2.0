package com.joss.voodootvdb.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.PersonAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.People.Cast;
import com.joss.voodootvdb.api.models.People.People;
import com.joss.voodootvdb.api.models.People.Person;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.VoodooClickListener;
import com.joss.voodootvdb.interfaces.VoodooItem;
import com.joss.voodootvdb.provider.person.PersonColumns;
import com.joss.voodootvdb.provider.person.PersonCursor;
import com.joss.voodootvdb.provider.person.PersonProvider;
import com.joss.voodootvdb.provider.person.PersonSelection;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesColumns;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesCursor;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesProvider;
import com.joss.voodootvdb.provider.person_movies.PersonMoviesSelection;
import com.joss.voodootvdb.provider.person_shows.PersonShowsColumns;
import com.joss.voodootvdb.provider.person_shows.PersonShowsCursor;
import com.joss.voodootvdb.provider.person_shows.PersonShowsProvider;
import com.joss.voodootvdb.provider.person_shows.PersonShowsSelection;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;
import com.joss.voodootvdb.views.PersonHeaderView;
import com.joss.voodootvdb.views.VoodooCardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/8/2015
 * Time: 4:34 PM
 */
public class PersonFragment extends BaseListFragment implements
        VoodooClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = PersonFragment.class.getSimpleName();
    private static final String TRAKT_ID = "trakt_id";
    private static final int PERSON_CALLBACK = 76489;
    private static final int PERSON_SHOWS_CALLBACK = 76490;
    private static final int PERSON_MOVIES_CALLBACK = 76491;

    private static final int TYPE_SHOW = 0;
    private static final int TYPE_MOVIE = 1;

    @Override
    View getLoadingView() {
        return new LoadingView(getActivity());
    }

    @Override
    View getErrorView() {
        return new ErrorView(getActivity());
    }

    @Override
    List<Integer> getApiTypes() {
        List<Integer> types = new ArrayList<>();
        types.add(ApiService.REQUEST_PERSON);
        return types;
    }

    @Override
    void onErrorMessageReceived() {
        // TODO Api.getPerson....
    }

    PersonHeaderView personHeaderView;
    PersonAdapter adapter;

    int showCastPosition = -1;
    List<VoodooItem> showCast;

    int movieCastPosition = -1;
    List<VoodooItem> movieCast;

    public static PersonFragment newInstance(int traktId) {
        Bundle args = new Bundle();
        args.putInt(TRAKT_ID, traktId);

        PersonFragment p = new PersonFragment();
        p.setArguments(args);
        return p;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showLoadingView();

        personHeaderView = new PersonHeaderView(getActivity());
        listView.addHeaderView(personHeaderView, null, false);

        adapter = new PersonAdapter(getActivity(), this);
        listView.setAdapter(adapter);

        getLoaderManager().initLoader(PERSON_CALLBACK, getArguments(), this);
        getLoaderManager().initLoader(PERSON_SHOWS_CALLBACK, getArguments(), this);
        getLoaderManager().initLoader(PERSON_MOVIES_CALLBACK, getArguments(), this);

        int traktId = getArguments().getInt(TRAKT_ID, 0);
        Api.getPerson(getActivity(), traktId);
        Api.getPersonShows(getActivity(), traktId);
        Api.getPersonMovies(getActivity(), traktId);

    }

    @Override
    public void onShowClicked(Show show) {

    }

    @Override
    public void onShowMenuClicked(Show show) {

    }

    @Override
    public void onTrailerClicked(String url) {

    }

    @Override
    public void onCastClicked(Cast cast) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        int traktId = args.getInt(TRAKT_ID, 0);
        switch (id){
            case PERSON_CALLBACK:
                PersonSelection wherePerson = new PersonSelection();
                wherePerson.traktId(traktId);
                return new CursorLoader(getActivity(),
                        PersonColumns.CONTENT_URI,
                        PersonColumns.FULL_PROJECTION,
                        wherePerson.sel(),
                        wherePerson.args(),
                        PersonColumns.DEFAULT_ORDER + " LIMIT 1");
            case PERSON_SHOWS_CALLBACK:
                PersonShowsSelection whereShow = new PersonShowsSelection();
                whereShow.traktId(traktId);
                return new CursorLoader(getActivity(),
                        PersonShowsColumns.CONTENT_URI,
                        PersonShowsColumns.FULL_PROJECTION,
                        whereShow.sel(),
                        whereShow.args(),
                        PersonShowsColumns.DEFAULT_ORDER + " LIMIT 1");
            case PERSON_MOVIES_CALLBACK:
                PersonMoviesSelection whereMovies = new PersonMoviesSelection();
                whereMovies.traktId(traktId);
                return new CursorLoader(getActivity(),
                        PersonMoviesColumns.CONTENT_URI,
                        PersonMoviesColumns.FULL_PROJECTION,
                        whereMovies.sel(),
                        whereMovies.args(),
                        PersonMoviesColumns.DEFAULT_ORDER + " LIMIT 1");
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            switch (loader.getId()){
                case PERSON_CALLBACK:
                    PersonCursor personCursor = new PersonCursor(data);
                    Person person = PersonProvider.get(personCursor);
                    if(person != null){
                        showContent();
                        personHeaderView.setContent(person);
                    }
                    break;

                case PERSON_SHOWS_CALLBACK:
                    PersonShowsCursor personShowsCursor = new PersonShowsCursor(data);
                    People people = PersonShowsProvider.get(personShowsCursor);
                    if(people != null){
                        List<VoodooItem> cast = PersonShowsProvider.get(VoodooCardView.TYPE_CAST_SHOW, getString(R.string.person_roles_small_screen), people);
                        if(cast.size() > 0 && !objectsEqual(TYPE_SHOW, cast, showCast)) {

                            if(showCastPosition != -1)
                                adapter.remove(showCastPosition);

                            this.showCast = cast;
                            this.showCastPosition = adapter.add(showCast);

                            personHeaderView.setBackground(people.getCast().get(0).getShow().getImages().getFanart().getFull());
                        }
                    }
                    break;

                case PERSON_MOVIES_CALLBACK:
                    PersonMoviesCursor moviesCursor = new PersonMoviesCursor(data);
                    People peopleMovies = PersonMoviesProvider.get(moviesCursor);
                    if(peopleMovies != null){
                        List<VoodooItem> castMovies = PersonMoviesProvider.get(VoodooCardView.TYPE_CAST_MOVIE, getString(R.string.person_roles_big_screen), peopleMovies);
                        if(castMovies.size() > 0 && !objectsEqual(TYPE_MOVIE, castMovies, movieCast)) {

                            if(movieCastPosition != -1)
                                adapter.remove(movieCastPosition);

                            this.movieCast = castMovies;
                            this.movieCastPosition = adapter.add(movieCast);

                            personHeaderView.setBackground(peopleMovies.getCast().get(0).getMovie().getImages().getFanart().getFull());
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private boolean objectsEqual(int type, List<VoodooItem> items1, List<VoodooItem> items2) {
        if(items1 == null || items2 == null)
            return false;

        // Check same size
        if(items1.size() != items2.size())
            return false;

        for(int i = 0; i < items1.size(); i++){

            // Check to see if they are the same class
            if(!items1.get(i).getClass().equals(items2.get(i).getClass()))
                return false;

            if(items1.get(i) instanceof Cast){
                Cast c1 = (Cast) items1.get(i);
                Cast c2 = (Cast) items2.get(i);

                int traktId1 = type == TYPE_SHOW
                        ? c1.getShow().getIds().getTrakt()
                        : c1.getMovie().getIds().getTrakt();
                int traktId2 = type == TYPE_SHOW
                        ? c2.getShow().getIds().getTrakt()
                        : c2.getMovie().getIds().getTrakt();

                if(traktId1 != traktId2)
                    return false;
            }
        }
        return true;
    }
}
