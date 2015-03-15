package com.joss.voodootvdb.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.SearchAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.Search.Search;
import com.joss.voodootvdb.interfaces.SearchListener;
import com.joss.voodootvdb.utils.GGson;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;
import com.joss.voodootvdb.views.SearchHeader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jossay
 * Date: 3/15/2015
 * Time: 1:46 PM
 */
public class SearchFragment extends BaseListFragment implements SearchListener{

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
    SearchAdapter adapter;
    String searchType;
    int page;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchApiReceiver = new SearchApiReceiver();
        adapter = new SearchAdapter(getActivity(), this);
        searchHeader = new SearchHeader(getActivity(), this);

        listView.addHeaderView(searchHeader, null, false);
        listView.setAdapter(adapter);

        searchType = Search.TYPE_ALL;
        page = 1;

        search(getArguments().getString(QUERY));
    }

    @Override
    public void onResume() {
        super.onResume();
        broadcastManager.registerReceiver(searchApiReceiver, new IntentFilter(ApiService.BROADCAST));
    }

    @Override
    public void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(searchApiReceiver);
    }

    public void search(String query) {
        showLoadingView();
        switch (searchType){
            case Search.TYPE_ALL:
                Api.searchAllTypes(getActivity(), query);
                break;

            case Search.TYPE_MOVIE:
            case Search.TYPE_SHOW:
            case Search.TYPE_PERSON:
                Api.search(getActivity(), query, searchType, page, LIMIT);
                break;
        }
    }

    private class SearchApiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int requestType = intent.getIntExtra(ApiService.REQUEST_TYPE, -1);
            int status = intent.getIntExtra(ApiService.RESULT_STATUS, ApiService.RESULT_ERROR);

            switch (requestType){
                case ApiService.REQUEST_SEARCH:
                    switch (status){
                        case ApiService.RESULT_SUCCESS:
                            List<Search> results = GGson.fromJson(
                                    intent.getStringExtra(ApiService.ARG_SEARCH_RESULTS),
                                    new TypeToken<List<Search>>(){}.getType());
                            if(results != null && results.size() > 0){
                                showContent();
                                adapter.setItems(results);
                            }else{
                                onErrorMessageReceived();
                            }

                        case ApiService.RESULT_ERROR:
                            onErrorMessageReceived();
                            break;
                    }
                    break;

                case ApiService.REQUEST_SEARCH_ALL_TYPES:
                    // TODO add or set depending
                    switch (status){
                        case ApiService.RESULT_SUCCESS:
                            List<Search> results = GGson.fromJson(
                                    intent.getStringExtra(ApiService.ARG_SEARCH_RESULTS),
                                    new TypeToken<List<Search>>(){}.getType());
                            if(results != null && results.size() > 0){
                                showContent();
                                adapter.setItems(results);
                            }else{
                                onErrorMessageReceived();
                            }

                        case ApiService.RESULT_ERROR:
                            onErrorMessageReceived();
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void onSearchTypeSelected(String type) {

    }

    @Override
    public void onSearchItemClicked(Search search) {

    }

    @Override
    public void onMoreSelected(Search search) {

    }
}
