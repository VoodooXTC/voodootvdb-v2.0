package com.joss.voodootvdb.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.adapters.MenuListAdapter;
import com.joss.voodootvdb.interfaces.BaseFragmementListener;
import com.joss.voodootvdb.model.DrawerModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:25 PM
 */
public abstract class BaseDrawerActivity extends BaseActivity implements BaseFragmementListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.top_padding)
    View topPadding;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.drawer_list_view)
    ListView drawerListView;
    @InjectView(R.id.content_container)
    FrameLayout contentView;

    abstract View getDrawerHeader();

    public MenuListAdapter adapter;
    public DrawerToggle drawerToggle;
    abstract List<DrawerModel> getDrawerItems();

    private String currentTitle;
    private String currentSubtitle;

    private int padding = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            padding = getStatusBarHeight();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setupDrawerLayout();
        setupToolbar(toolbar);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(GravityCompat.START);
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isEnabled()) {
                menu.getItem(i).setVisible(!drawerOpen);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.isDrawerIndicatorEnabled()) {
            if (drawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSetActionBarTitle(String title, String subtitle) {
        setTitlesAndSave(title, subtitle);
    }

    public void setTitlesAndSave(String title, String subtitle) {
        setToolbarTitles(title, subtitle);
        currentTitle = title;
        currentSubtitle = subtitle;
    }

    private void setupDrawerLayout() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, padding);
        topPadding.setLayoutParams(params);

        View header = getDrawerHeader();
        if(header != null)
            drawerListView.addHeaderView(header, null, false);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        adapter = new MenuListAdapter(this, getDrawerItems());
        drawerListView.setAdapter(adapter);
        drawerToggle = new DrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class DrawerToggle extends ActionBarDrawerToggle {

        public DrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        /** Called when a drawer has settled in a completely closed state. */
        @Override
        public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
            setTitlesAndSave(currentTitle, currentSubtitle);
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }

        /** Called when a drawer has settled in a completely open state. */
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            setToolbarTitles(getString(R.string.app_name), null);
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }
    }

    public void closeDrawer() {
        if (drawerLayout.isShown()) {
            drawerLayout.closeDrawers();
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
