package com.joss.voodootvdb.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.ListsPagerAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.provider.lists.ListsColumns;
import com.joss.voodootvdb.provider.lists.ListsCursor;
import com.joss.voodootvdb.provider.lists.ListsProvider;
import com.joss.voodootvdb.views.EmptyView;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;
import com.joss.voodootvdb.views.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import oak.util.OakUtils;

/**
 * Created by: jossayjacobo
 * Date: 3/2/15
 * Time: 5:57 PM
 */
public class FavoritesFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = FavoritesFragment.class.getSimpleName();
    private static final int LISTS_CALLBACK = 17348;

    @InjectView(R.id.favorites_viewpager_tabs)
    PagerSlidingTabStrip tabs;
    @InjectView(R.id.favorites_viewpager)
    ViewPager pager;
    @InjectView(R.id.favorites_error_view)
    ErrorView errorView;
    @InjectView(R.id.favorites_empty)
    EmptyView emptyView;
    @InjectView(R.id.favorites_loading)
    LoadingView loadingView;

    protected LocalBroadcastManager broadcastManager;
    private ApiReceiver apiReceiver;

    List<CustomList> lists;
    ListsPagerAdapter adapter;

    @Override
    int getLayoutId() {
        return R.layout.fragment_favorites;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        apiReceiver = new ApiReceiver();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lists = new ArrayList<>();
        emptyView.setText(getString(R.string.favorites_empty));
        getLoaderManager().initLoader(LISTS_CALLBACK, null, this);

        // Add Watchlist
        lists.add(getWatchlist());

        adapter = new ListsPagerAdapter(getChildFragmentManager(), lists);
        pager.setAdapter(adapter);

        tabs.setTypeface(OakUtils.getStaticTypeFace(getActivity(), getString(R.string.font_roboto_thin)), 0);
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        tabs.setViewPager(pager);

        showContent();
    }

    @Override
    public void onResume(){
        super.onResume();
        broadcastManager.registerReceiver(apiReceiver, new IntentFilter(ApiService.BROADCAST));
        if(lists.size() <= 1){
            Api.getLists(getActivity());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(apiReceiver);
    }

    private class ApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int requestType = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            if(requestType == ApiService.REQUEST_USER_LISTS){
                int status = intent.getIntExtra(ApiService.RESULT_STATUS, ApiService.RESULT_ERROR);
                if (status == ApiService.RESULT_ERROR) {
                    switch (intent.getIntExtra(ApiService.ERROR_TYPE, ApiService.ERROR_RESPONSE)){
                        case ApiService.ERROR_EMPTY:
                            onEmptyMessageReceived();
                            break;
                        default:
                            onErrorMessageReceived();
                    }
                }
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                ListsColumns.CONTENT_URI,
                ListsColumns.FULL_PROJECTION,
                null,
                null,
                ListsColumns.NAME + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            ListsCursor cursor = new ListsCursor(data);
            List<CustomList> customLists = ListsProvider.get(cursor);

            if(customLists.size() > 0){
                customLists.add(0, getWatchlist());
                if(!adapter.equals(customLists)){
                    lists = customLists;
                    adapter.setItems(lists);
                    tabs.setViewPager(pager);
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public CustomList getWatchlist() {
        return new CustomList()
                .setName(getString(R.string.watchlist))
                .setTraktId(CustomList.WATCHLIST_ID);
    }

    private void onEmptyMessageReceived() {
        if(lists == null || lists.size() == 0)
            showEmpty();
    }

    private void onErrorMessageReceived() {
        if(lists == null || lists.size() == 0)
            showError();
    }

    private void showContent(){
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    private void showLoading(){
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    private void showEmpty() {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    private void showError(){
        errorView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }
}
