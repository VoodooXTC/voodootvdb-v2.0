package com.joss.voodootvdb.activities;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.joss.voodootvdb.R;
import com.joss.voodootvdb.api.ApiService;

import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by: jossayjacobo
 * Date: 2/27/15
 * Time: 4:12 PM
 */
public class BaseActivity extends ActionBarActivity {

    @Optional
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

    public void toast(String message){
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT)
                .show();
    }

    public void hideSoftKeyboard(View view){
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
