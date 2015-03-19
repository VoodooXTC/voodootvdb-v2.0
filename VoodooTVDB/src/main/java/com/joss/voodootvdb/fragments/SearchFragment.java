package com.joss.voodootvdb.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.activities.MovieActivity;
import com.joss.voodootvdb.activities.PersonActivity;
import com.joss.voodootvdb.activities.ShowActivity;
import com.joss.voodootvdb.adapters.SearchAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.interfaces.SearchListener;
import com.joss.voodootvdb.utils.GGson;
import com.joss.voodootvdb.utils.Utils;
import com.joss.voodootvdb.views.EmptyView;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingListItemView;
import com.joss.voodootvdb.views.LoadingView;
import com.joss.voodootvdb.views.SearchHeader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 1:46 PM
 */
public class SearchFragment extends BaseListFragment implements SearchListener, SearchView.OnQueryTextListener {

    public static final String TAG = SearchFragment.class.getSimpleName();
    private static final String QUERY = "query";
    private static final int LIMIT = 10;

    public static SearchFragment getInstance(String query){
        Bundle args = new Bundle();
        args.putString(QUERY, query);

        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(args);

        return searchFragment;
    }

    @Override
    View getLoadingView() {
        return new LoadingView(getActivity());
    }

    @Override
    View getErrorView() {
        return new ErrorView(getActivity());
    }

    @Override
    View getEmptyView() {
        emptyView = new EmptyView(getActivity());
        return emptyView;
    }

    @Override
    List<Integer> getApiTypes() {
        return Arrays.asList(ApiService.REQUEST_SEARCH);
    }

    @Override
    void onErrorMessageReceived() {
        if(adapter.items.size() <= 1)
            showErrorView();
    }

    SearchApiReceiver searchApiReceiver;
    SearchHeader searchHeader;
    LoadingListItemView loadingListItemView;
    SearchAdapter adapter;
    SearchView searchView;
    EmptyView emptyView;
    String searchType = Search.TYPE_ALL;
    int page;

    String query = "";
    boolean searching = false;
    boolean loadingListViewAdded;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchApiReceiver = new SearchApiReceiver();
        adapter = new SearchAdapter(getActivity(), this);
        searchHeader = new SearchHeader(getActivity(), this);
        loadingListItemView = new LoadingListItemView(getActivity());

        listView.addHeaderView(searchHeader, null, false);
        listView.setAdapter(adapter);

        searchType = Search.TYPE_ALL;
        page = 1;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter.items.size() == 0)
            search(getArguments().getString(QUERY));

        broadcastManager.registerReceiver(searchApiReceiver, new IntentFilter(ApiService.BROADCAST));
    }

    @Override
    public void onPause() {
        super.onPause();
        searching = false;
        broadcastManager.unregisterReceiver(searchApiReceiver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_search, menu);

        // Search Menu Item
        MenuItem search = menu.findItem(R.id.menu_search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setIconifiedByDefault(false);
        searchView.setQuery(query, false);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Utils.hideSoftKeyboard(getActivity(), searchView);
        clearFlags();
        search(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void search(String q) {
        if(!searching){
            listView.requestFocus();
            searching = true;
            query = q;
            if(searchView != null)
                searchView.setQuery(query, false);

            switch (searchType){
                case Search.TYPE_ALL:
                    showLoadingView();
                    adapter.clearItems();

                    Api.searchAllTypes(getActivity(), query);
                    removeLoadingFooter();
                    break;

                case Search.TYPE_MOVIE:
                case Search.TYPE_SHOW:
                case Search.TYPE_PERSON:
                    if(adapter.items.size() == 0)
                        showLoadingView();

                    Api.search(getActivity(), query, searchType, page, LIMIT);
                    addLoadingFooter();
                    break;
            }
        }
    }

    @Override
    public void onLoadMore(){
        if(!searching && !searchType.equals(Search.TYPE_ALL)){
            page++;
            search(query);
        }
    }

    private class SearchApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int requestType = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            int status = intent.getIntExtra(ApiService.RESULT_STATUS, ApiService.RESULT_ERROR);

            switch (requestType){
                case ApiService.REQUEST_SEARCH_ALL_TYPES:
                    switch (status){
                        case ApiService.RESULT_SUCCESS:
                            List<Search> results = GGson.fromJson(
                                    intent.getStringExtra(ApiService.ARG_SEARCH_RESULTS),
                                    new TypeToken<List<Search>>(){}.getType());

                            showContent();
                            if(results != null && results.size() > 0){
                                searching = false;
                                adapter.setItems(results);
                            }else{
                                searching = true;
                                emptyView.setText(getString(R.string.empty_text) + " for " + query);
                                removeLoadingFooter();
                            }
                            break;

                        case ApiService.RESULT_ERROR:
                            searching = false;
                            onErrorMessageReceived();
                            break;
                    }
                    break;

                case ApiService.REQUEST_SEARCH:
                    switch (status){
                        case ApiService.RESULT_SUCCESS:
                            List<Search> results = GGson.fromJson(
                                    intent.getStringExtra(ApiService.ARG_SEARCH_RESULTS),
                                    new TypeToken<List<Search>>(){}.getType());

                            showContent();
                            if(results != null && results.size() > 0){
                                searching = false;
                                adapter.addItems(results);
                            }else{
                                searching = true;
                                emptyView.setText(getString(R.string.empty_text) + " for " + query);
                                removeLoadingFooter();
                            }
                            break;

                        case ApiService.RESULT_ERROR:
                            searching = false;
                            onErrorMessageReceived();
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void onSearchTypeSelected(String type) {
        if(!searchType.equals(type)){
            clearFlags();
            searchType = type;
            search(query);
        }
    }


    @Override
    public void onSearchItemClicked(Search search) {
        switch (search.type){
            case Search.TYPE_MOVIE:
                MovieActivity.startActivity(getActivity(), search.movie.getId());
                break;

            case Search.TYPE_SHOW:
                ShowActivity.startActivity(getActivity(), search.show.getId());
                break;

            case Search.TYPE_PERSON:
                PersonActivity.startActivity(getActivity(), search.person.getIds().getTrakt());
                break;
        }
    }

    @Override
    public void onMoreSelected(String searchType) {
        searchHeader.setType(searchType);
        onSearchTypeSelected(searchType);
    }

    private void addLoadingFooter(){
        if(!loadingListViewAdded){
            loadingListViewAdded = true;
            listView.addFooterView(loadingListItemView, null, false);
        }
    }

    private void removeLoadingFooter(){
        if(loadingListViewAdded){
            loadingListViewAdded = false;
            listView.removeFooterView(loadingListItemView);
        }
    }

    private void clearFlags() {
        adapter.clearItems();
        page = 1;
        searching = false;
    }
}
