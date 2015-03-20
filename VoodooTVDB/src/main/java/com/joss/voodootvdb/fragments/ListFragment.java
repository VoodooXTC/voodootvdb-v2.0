package com.joss.voodootvdb.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.ListAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.ApiService;
import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.api.models.CustomLists.CustomListItem;
import com.joss.voodootvdb.interfaces.ListListener;
import com.joss.voodootvdb.provider.list_items.ListItemsColumns;
import com.joss.voodootvdb.provider.list_items.ListItemsCursor;
import com.joss.voodootvdb.provider.list_items.ListItemsProvider;
import com.joss.voodootvdb.provider.list_items.ListItemsSelection;
import com.joss.voodootvdb.utils.GGson;
import com.joss.voodootvdb.views.EmptyView;
import com.joss.voodootvdb.views.ErrorView;
import com.joss.voodootvdb.views.LoadingView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by: jossayjacobo
 * Date: 3/20/15
 * Time: 3:01 PM
 */
public class ListFragment extends BaseListFragment implements LoaderManager.LoaderCallbacks<Cursor>,ListListener {

    public static final String TAG = ListFragment.class.getSimpleName();
    private static final String LIST = "list";
    private static final int LIST_ITEMS_CALLBACK = 2835;

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
        return new EmptyView(getActivity()).setText(getString(R.string.list_empty));
    }

    @Override
    List<Integer> getApiTypes() {
    return Arrays.asList(ApiService.REQUEST_USER_LIST_ITEMS);
    }

    @Override
    void onErrorMessageReceived() {
        if(adapter.items.size() == 0)
            showErrorView();
    }

    public static ListFragment getInstance(CustomList list){
        Bundle args = new Bundle();
        args.putString(LIST, GGson.toJson(list));

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    CustomList list;
    List<CustomListItem> listItems;
    ListAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listItems = new ArrayList<>();
        getLoaderManager().initLoader(LIST_ITEMS_CALLBACK, getArguments(), this);

        list = GGson.fromJson(getArguments().getString(LIST), CustomList.class);
        adapter = new ListAdapter(getActivity(), this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(listItems.size() == 0){
            showLoadingView();
            Api.getListItems(getActivity(), list.getIds().getTrakt());
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CustomList list = GGson.fromJson(args.getString(LIST), CustomList.class);

        ListItemsSelection where = new ListItemsSelection();
        where.listTraktId(list.getIds().getTrakt());

        return new CursorLoader(getActivity(),
                ListItemsColumns.CONTENT_URI,
                ListItemsColumns.FULL_PROJECTION,
                where.sel(),
                where.args(),
                ListItemsColumns.DEFAULT_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null){
            ListItemsCursor cursor = new ListItemsCursor(data);
            listItems = ListItemsProvider.get(cursor);
            if(listItems.size() > 0){
                showContent();
                adapter.setItems(listItems);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
