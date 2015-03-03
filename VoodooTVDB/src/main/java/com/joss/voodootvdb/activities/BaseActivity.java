package com.joss.voodootvdb.activities;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.joss.voodootvdb.R;

import butterknife.InjectView;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:12 PM
 */
public class BaseActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    public Toolbar toolbar;

    protected void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void setToolbarTitle(String title) {
        setToolbarTitles(title, null);
    }

    public void setToolbarTitles(String title, String subtitle) {
        if(toolbar != null){
            toolbar.setTitle(title);
            toolbar.setSubtitle(subtitle);
        }
    }
}
