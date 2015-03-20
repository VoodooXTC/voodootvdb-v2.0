package com.joss.voodootvdb.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.ListsPagerAdapter;
import com.joss.voodootvdb.api.Api;
import com.joss.voodootvdb.api.models.CustomLists.CustomList;
import com.joss.voodootvdb.provider.lists.ListsColumns;
import com.joss.voodootvdb.provider.lists.ListsCursor;
import com.joss.voodootvdb.provider.lists.ListsProvider;
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
    @InjectView(R.id.favorites_loading)
    LoadingView loadingView;

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
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lists = new ArrayList<>();
        getLoaderManager().initLoader(LISTS_CALLBACK, null, this);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(lists.size() == 0){
            showLoading();
            Api.getLists(getActivity());
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
            lists = ListsProvider.get(cursor);
            if(lists.size() > 0){
                showContent();

                if(adapter == null)
                    adapter = new ListsPagerAdapter(getChildFragmentManager(), lists);

                pager.setAdapter(adapter);

                tabs.setTypeface(OakUtils.getStaticTypeFace(getActivity(), getString(R.string.font_roboto_thin)), 0);
                tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                tabs.setViewPager(pager);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void showContent(){
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    private void showLoading(){
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    private void showError(){
        errorView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }
}
