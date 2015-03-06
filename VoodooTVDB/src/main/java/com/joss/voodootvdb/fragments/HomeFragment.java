package com.joss.voodootvdb.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.HomeAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Show.Show;
import com.joss.voodootvdb.interfaces.HomeClickListener;
import com.joss.voodootvdb.interfaces.HomeItem;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularColumns;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularCursor;
import com.joss.voodootvdb.provider.shows_popular.ShowsPopularProvider;
import com.joss.voodootvdb.utils.Utils;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.HomeHorizontalScrollView;
import com.joss.voodootvdb.views.LoadingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/2/15
 * Time: 5:57 PM
 */
public class HomeFragment extends BaseListFragment implements AdapterView.OnItemClickListener,
        LoaderManager.LoaderCallbacks<Cursor>, HomeClickListener {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private static final int POPULAR_SHOWS_CALLBACK = 456;

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
        types.add(ApiService.REQUEST_POPULAR_SHOWS);
        return types;
    }

    @Override
    void onErrorMessageReceived() {
        // TODO if adapter.items.size() == 0 show error view
    }

    HomeAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listener.onSetToolbarTitles(getString(R.string.drawer_home), null);

        adapter = new HomeAdapter(getActivity(), this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        getLoaderManager().initLoader(POPULAR_SHOWS_CALLBACK, null, this);

        showLoadingView();
        Api.getPopularShows(getActivity(), ApiService.EXT_FULL, ApiService.EXT_IMAGES);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                ShowsPopularColumns.CONTENT_URI,
                ShowsPopularColumns.FULL_PROJECTION,
                null,
                null,
                ShowsPopularColumns.DEFAULT_ORDER + " DESC LIMIT 5");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            ShowsPopularCursor cursor = new ShowsPopularCursor(data);
            List<HomeItem> shows = ShowsPopularProvider.getHomeItems(cursor,
                    HomeHorizontalScrollView.TYPE_FEATURE,
                    "Featured");

            if(shows.size() > 0)
                showContent();

            List<List<HomeItem>> items = new ArrayList<>();
            items.add(shows);
            items.add(ShowsPopularProvider.getHomeItems(cursor, HomeHorizontalScrollView.TYPE_NORMAL, "Recommended"));
            items.add(ShowsPopularProvider.getHomeItems(cursor, HomeHorizontalScrollView.TYPE_NORMAL, "Action/Adventure"));
            items.add(ShowsPopularProvider.getHomeItems(cursor, HomeHorizontalScrollView.TYPE_NORMAL, "New Releases"));

            adapter.setContent(items);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onShowClicked(Show show) {
        Utils.toast(getActivity(), show.getTitle() + " : Show Clicked");
    }

    @Override
    public void onShowMenuClicked(Show show) {
        // TODO maybe move this into the actual view??
        Utils.toast(getActivity(), show.getTitle() + " : Show Menu Clicked");
    }

    @Override
    public void onTrailerClicked(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
